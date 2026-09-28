package com.example.investigacion02_jetpack_compose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.investigacion02_jetpack_compose.model.Product
import com.example.investigacion02_jetpack_compose.ui.theme.Investigacion02JetpackComposeTheme

/**
 * Componente @Composable complejo y reutilizable: Tarjeta de Producto.
 *
 * Aplica los 3 contenedores fundamentales de Jetpack Compose:
 * - BOX: Superpone el Badge de descuento y el botón de favoritos sobre el contenedor de imagen.
 * - COLUMN: Distribuye verticalmente categoría, título, descripción, rating y precio.
 * - ROW: Alinea horizontalmente el precio con el botón de compra y los elementos de puntuación.
 */
@Composable
fun ProductCard(
    product: Product,
    isFavorite: Boolean,
    onToggleFavorite: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // 1. USO DE BOX: Capa visual (Banner visual con Emoji + Badge superpuesto + Botón Favorito)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                // Icono / Emoji central de producto
                Text(
                    text = product.iconEmoji,
                    fontSize = 54.sp
                )

                // Badge de oferta posicionado en la esquina superior izquierda
                if (product.badge != null) {
                    ProductBadge(
                        text = product.badge,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(10.dp)
                    )
                }

                // Botón de favorito posicionado en la esquina superior derecha
                IconButton(
                    onClick = { onToggleFavorite(product) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.85f))
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = if (isFavorite) Color(0xFFE11D48) else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // 2. USO DE COLUMN: Contenido textual organizado en jerarquía vertical
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Categoría
                Text(
                    text = product.category.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Título del producto
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Descripción breve
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rating bar reutilizado
                ProductRatingBar(
                    rating = product.rating,
                    reviewCount = product.reviewCount
                )

                Spacer(modifier = Modifier.height(14.dp))

                // 3. USO DE ROW: Fila horizontal para alinear Precio y Botón de Acción
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Precio actual y de descuento reutilizado
                    PriceTag(
                        currentPrice = product.price,
                        originalPrice = product.originalPrice
                    )

                    // Botón para añadir al carrito
                    Button(
                        onClick = { onAddToCart(product) },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Agregar",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        Text(
                            text = "Agregar",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "ProductCard Claro", showBackground = true)
@Composable
fun ProductCardLightPreview() {
    Investigacion02JetpackComposeTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            ProductCard(
                product = Product(
                    id = 1,
                    title = "MacBook Pro 16\" M3 Max",
                    description = "Potencia extrema con 36GB de memoria unificada y pantalla Liquid Retina XDR de 120Hz.",
                    category = "Laptops",
                    price = 2499.99,
                    originalPrice = 2899.99,
                    rating = 4.9,
                    reviewCount = 340,
                    badge = "TOP VENTAS",
                    iconEmoji = "💻"
                ),
                isFavorite = true,
                onToggleFavorite = {},
                onAddToCart = {}
            )
        }
    }
}

@Preview(name = "ProductCard Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ProductCardDarkPreview() {
    Investigacion02JetpackComposeTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            ProductCard(
                product = Product(
                    id = 2,
                    title = "Sony WH-1000XM5",
                    description = "Cancelación de ruido líder en la industria y sonido de alta fidelidad.",
                    category = "Audio",
                    price = 349.99,
                    originalPrice = 399.99,
                    rating = 4.8,
                    reviewCount = 890,
                    badge = "OFERTA",
                    iconEmoji = "🎧"
                ),
                isFavorite = false,
                onToggleFavorite = {},
                onAddToCart = {}
            )
        }
    }
}
