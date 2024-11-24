// Archivo: AppNavigation.kt
package com.example.jedapaappf1

import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("signup") { RegisterScreen(navController) }
        composable("register_confirmation") { RegisterConfirmationScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("driversResults"){ResultsScreen(navController)}      //Falta añadir una variable
        composable("teamsResults"){ResultsScreen(navController)}        //Falta añadir una variable
        composable("teams"){ TeamsDriversScreen(navController) }        //Falta añadir una variable
        composable("drivers"){ TeamsDriversScreen(navController) }      //Falta añadir una variable
        composable("calendar"){ CalendarScreen(navController) }      //Falta añadir una variable
        composable("formulaLearning"){ FormulaLearningScreen(navController) }      //Falta añadir una variable
    }
}

