// Archivo: HomeScreen.kt
package com.example.jedapaappf1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    JedapaF1AppImage(
        name = "Nombre",
        modifier = Modifier.fillMaxSize(),
        onSignUpClick = { navController.navigate("signup") },
        onLoginClick = { navController.navigate("login") }
    )
}

