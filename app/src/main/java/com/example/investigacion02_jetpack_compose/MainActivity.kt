package com.example.investigacion02_jetpack_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investigacion02_jetpack_compose.ui.screens.CatalogScreen
import com.example.investigacion02_jetpack_compose.ui.theme.Investigacion02JetpackComposeTheme

/**
 * Actividad principal de la aplicación.
 * Integra la pantalla declarativa dentro de un Scaffold de Material 3.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Investigacion02JetpackComposeTheme {
                MainApp()
            }
        }
    }
}

/**
 * Estructura raíz de la interfaz con TopAppBar y contenedor Scaffold.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "TechStore Compose",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.Storefront,
                        contentDescription = "Logo",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        CatalogScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(name = "App Completa - Claro", showBackground = true, showSystemUi = true)
@Composable
fun MainAppLightPreview() {
    Investigacion02JetpackComposeTheme(darkTheme = false) {
        MainApp()
    }
}

@Preview(name = "App Completa - Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun MainAppDarkPreview() {
    Investigacion02JetpackComposeTheme(darkTheme = true) {
        MainApp()
    }
}