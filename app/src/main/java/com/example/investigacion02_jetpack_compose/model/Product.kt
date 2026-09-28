package com.example.investigacion02_jetpack_compose.model

/**
 * Modelo de datos inmutable que representa un producto dentro del catálogo.
 * Facilita el paso de datos a las funciones @Composable de forma declarativa.
 */
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val originalPrice: Double? = null,
    val rating: Double,
    val reviewCount: Int,
    val badge: String? = null,
    val iconEmoji: String = "💻" // Emoji representativo para visualización rápida y limpia
)
