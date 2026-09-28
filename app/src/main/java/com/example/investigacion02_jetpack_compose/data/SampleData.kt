package com.example.investigacion02_jetpack_compose.data

import com.example.investigacion02_jetpack_compose.model.Product

object SampleData {
    val categories = listOf(
        "Todos",
        "Laptops",
        "Smartphones",
        "Audio",
        "Gaming",
        "Wearables"
    )

    val products = listOf(
        Product(
            id = 1,
            title = "MacBook Pro 16\" M3 Max",
            description = "CPU de 16 núcleos, GPU de 40 núcleos, 36GB de memoria unificada y 1TB SSD. Pantalla Liquid Retina XDR.",
            category = "Laptops",
            price = 2499.99,
            originalPrice = 2899.99,
            rating = 4.9,
            reviewCount = 342,
            badge = "TOP VENTAS",
            iconEmoji = "💻"
        ),
        Product(
            id = 2,
            title = "iPhone 16 Pro Max 256GB",
            description = "Estructura de titanio aeroespacial, chip A18 Pro, Botón de Acción y cámara con teleobjetivo 5x.",
            category = "Smartphones",
            price = 1199.00,
            originalPrice = 1299.00,
            rating = 4.8,
            reviewCount = 512,
            badge = "NUEVO",
            iconEmoji = "📱"
        ),
        Product(
            id = 3,
            title = "Sony WH-1000XM5 Noise Cancelling",
            description = "Procesador integrado V1, sonido de alta resolución sin cables y hasta 30 horas de batería continua.",
            category = "Audio",
            price = 349.99,
            originalPrice = 399.99,
            rating = 4.7,
            reviewCount = 890,
            badge = "OFERTA -12%",
            iconEmoji = "🎧"
        ),
        Product(
            id = 4,
            title = "PlayStation 5 Pro Console",
            description = "Gráficos con trazado de rayos avanzado, almacenamiento ultrarrápido SSD de 2TB y compatibilidad 4K a 120fps.",
            category = "Gaming",
            price = 699.99,
            rating = 4.9,
            reviewCount = 1240,
            badge = "EXCLUSIVO",
            iconEmoji = "🎮"
        ),
        Product(
            id = 5,
            title = "Apple Watch Ultra 2 GPS + Cellular",
            description = "Caja de titanio de 49mm, resistencia al agua hasta 100m, pantalla de 3000 nits y botón de acción personalizable.",
            category = "Wearables",
            price = 799.00,
            originalPrice = 849.00,
            rating = 4.8,
            reviewCount = 275,
            badge = "POPULAR",
            iconEmoji = "⌚"
        ),
        Product(
            id = 6,
            title = "iPad Pro 13\" M4 OLED",
            description = "El diseño más fino de Apple con pantalla Ultra Retina XDR Tandem OLED y compatibilidad con Apple Pencil Pro.",
            category = "Laptops",
            price = 1299.00,
            rating = 4.8,
            reviewCount = 190,
            iconEmoji = "📟"
        ),
        Product(
            id = 7,
            title = "Bose QuietComfort Ultra Earbuds",
            description = "Audio espacial inmersivo con cancelación de ruido de primer nivel y calibración acústica CustomTune.",
            category = "Audio",
            price = 279.00,
            originalPrice = 299.00,
            rating = 4.6,
            reviewCount = 430,
            badge = "OFERTA",
            iconEmoji = "🎵"
        )
    )
}
