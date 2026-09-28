package com.example.investigacion02_jetpack_compose.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.investigacion02_jetpack_compose.data.SampleData
import com.example.investigacion02_jetpack_compose.model.Product
import com.example.investigacion02_jetpack_compose.ui.components.CartSummaryBar
import com.example.investigacion02_jetpack_compose.ui.components.CategoryChip
import com.example.investigacion02_jetpack_compose.ui.components.ProductCard
import com.example.investigacion02_jetpack_compose.ui.theme.Investigacion02JetpackComposeTheme
import kotlinx.coroutines.launch

/**
 * Pantalla principal del catálogo interactivo.
 *
 * Aplica:
 * - LazyRow: Desplazamiento horizontal fluido de categorías.
 * - LazyColumn: Reciclaje y renderizado eficiente de tarjetas de producto con 'items(key)'.
 * - Gestión de Estado: Filtrado en tiempo real por categoría y término de búsqueda.
 * - Composición reactiva del carrito y favoritos.
 */
@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier
) {
    // Estados observables que provocan recomposiciones declarativas
    var selectedCategory by remember { mutableStateOf("Todos") }
    var searchQuery by remember { mutableStateOf("") }
    val favoriteIds = remember { mutableStateListOf(1, 4) }
    val cartProducts = remember { mutableStateListOf<Product>() }

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Filtrado reactivo derivado del estado
    val filteredProducts = remember(selectedCategory, searchQuery) {
        SampleData.products.filter { product ->
            val matchesCategory = (selectedCategory == "Todos" || product.category.equals(selectedCategory, ignoreCase = true))
            val matchesSearch = product.title.contains(searchQuery, ignoreCase = true) ||
                    product.description.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    val totalCartPrice = remember(cartProducts.size) {
        cartProducts.sumOf { it.price }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Columna de contenido principal
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 1. Barra de búsqueda superior
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Buscar laptops, audífonos, consolas...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "Limpiar")
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            // 2. USO DE LazyRow: Carrusel horizontal de categorías
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(SampleData.categories) { category ->
                    CategoryChip(
                        text = category,
                        isSelected = category == selectedCategory,
                        onClick = { selectedCategory = category }
                    )
                }
            }

            // Indicador de resultados
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${filteredProducts.size} productos encontrados",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
                if (favoriteIds.isNotEmpty()) {
                    Text(
                        text = "❤️ ${favoriteIds.size} favoritos",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // 3. USO DE LazyColumn: Lista eficiente de tarjetas de producto
            if (filteredProducts.isEmpty()) {
                // Estado vacío cuando no hay coincidencias
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Outlined.Inventory2,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No se encontraron productos",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Intenta con otra búsqueda o categoría",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = if (cartProducts.isNotEmpty()) 100.dp else 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        items = filteredProducts,
                        key = { product -> product.id }
                    ) { product ->
                        val isFav = favoriteIds.contains(product.id)
                        ProductCard(
                            product = product,
                            isFavorite = isFav,
                            onToggleFavorite = {
                                if (isFav) {
                                    favoriteIds.remove(product.id)
                                } else {
                                    favoriteIds.add(product.id)
                                }
                            },
                            onAddToCart = {
                                cartProducts.add(product)
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "¡${product.title} añadido al carrito!"
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }

        // 4. Barra flotante de carrito superpuesta en la parte inferior
        CartSummaryBar(
            itemCount = cartProducts.size,
            totalPrice = totalCartPrice,
            onCheckout = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Procesando pago de $${String.format(java.util.Locale.US, "%.2f", totalCartPrice)}")
                }
            },
            onClearCart = {
                cartProducts.clear()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )

        // Host para notificaciones Snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(name = "Pantalla Catalogo Claro", showBackground = true)
@Composable
fun CatalogScreenLightPreview() {
    Investigacion02JetpackComposeTheme(darkTheme = false) {
        CatalogScreen()
    }
}

@Preview(name = "Pantalla Catalogo Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CatalogScreenDarkPreview() {
    Investigacion02JetpackComposeTheme(darkTheme = true) {
        CatalogScreen()
    }
}
