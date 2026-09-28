package com.example.investigacion02_jetpack_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investigacion02_jetpack_compose.ui.theme.Investigacion02JetpackComposeTheme

/**
 * Componente @Composable que ilustra el concepto de "State Hoisting" (Elevación de Estado).
 *
 * Versión Stateless (Sin estado interno):
 * Recibe el estado actual (quantity) y notifica eventos a través de callbacks (onQuantityChange).
 */
@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minQuantity: Int = 1,
    maxQuantity: Int = 99
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Botón Disminuir (-)
        IconButton(
            onClick = {
                if (quantity > minQuantity) {
                    onQuantityChange(quantity - 1)
                }
            },
            enabled = quantity > minQuantity,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = "Disminuir cantidad",
                tint = if (quantity > minQuantity) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Texto con el estado actual
        Text(
            text = "$quantity",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Botón Incrementar (+)
        IconButton(
            onClick = {
                if (quantity < maxQuantity) {
                    onQuantityChange(quantity + 1)
                }
            },
            enabled = quantity < maxQuantity,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Aumentar cantidad",
                tint = if (quantity < maxQuantity) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

/**
 * Versión Stateful (Con estado interno gestionado por 'remember' y 'mutableIntStateOf').
 * Encapsula su propio estado para uso independiente o pruebas en Previews.
 */
@Composable
fun StatefulQuantitySelector(
    initialQuantity: Int = 1,
    onQuantityConfirmed: (Int) -> Unit = {}
) {
    // Declaración explícita del estado en Jetpack Compose
    var currentQuantity by remember { mutableIntStateOf(initialQuantity) }

    QuantitySelector(
        quantity = currentQuantity,
        onQuantityChange = { newQty ->
            currentQuantity = newQty
            onQuantityConfirmed(newQty)
        }
    )
}

@Preview(name = "QuantitySelector Preview", showBackground = true)
@Composable
fun QuantitySelectorPreview() {
    Investigacion02JetpackComposeTheme {
        StatefulQuantitySelector(initialQuantity = 2)
    }
}
