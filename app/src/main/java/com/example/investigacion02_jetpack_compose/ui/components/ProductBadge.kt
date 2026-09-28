package com.example.investigacion02_jetpack_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investigacion02_jetpack_compose.ui.theme.Investigacion02JetpackComposeTheme

/**
 * Componente @Composable reutilizable para mostrar insignias o etiquetas (Badges/Chips).
 * Demuestra el uso de Modificadores: clip, background, padding y alineación declarativa.
 */
@Composable
fun ProductBadge(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(name = "Badge Preview", showBackground = true)
@Composable
fun ProductBadgePreview() {
    Investigacion02JetpackComposeTheme {
        ProductBadge(text = "🔥 OFERTA -20%")
    }
}
