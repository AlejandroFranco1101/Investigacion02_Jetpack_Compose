package com.example.investigacion02_jetpack_compose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investigacion02_jetpack_compose.ui.theme.Investigacion02JetpackComposeTheme

/**
 * Componente @Composable reutilizable para renderizar precios con soporte para descuentos.
 * Demuestra composición condicional, modificadores de texto y estilos de tipografía.
 */
@Composable
fun PriceTag(
    currentPrice: Double,
    originalPrice: Double? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        // Precio actual principal
        Text(
            text = "$${String.format(java.util.Locale.US, "%.2f", currentPrice)}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )

        // Precio original tachado si hay descuento
        if (originalPrice != null && originalPrice > currentPrice) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$${String.format(java.util.Locale.US, "%.2f", originalPrice)}",
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = TextDecoration.LineThrough,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview(name = "PriceTag con Descuento", showBackground = true)
@Composable
fun PriceTagWithDiscountPreview() {
    Investigacion02JetpackComposeTheme {
        PriceTag(currentPrice = 79.99, originalPrice = 99.99)
    }
}
