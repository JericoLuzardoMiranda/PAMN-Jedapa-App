// Archivo: AppNavigation.kt
package com.example.jedapaappf1

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, userViewModel) }
        composable("signup") { RegisterScreen(navController, userViewModel) }
        composable("register_confirmation") { RegisterConfirmationScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("driversResults"){ResultsScreen(navController, userViewModel, isTeamsResults = false)}
        composable("teamsResults"){ResultsScreen(navController, userViewModel, isTeamsResults = true)}
        composable("teams"){ TeamsDriversScreen(navController, userViewModel, isTeams = true)  }
        composable("drivers"){ TeamsDriversScreen(navController, userViewModel, isTeams = false) }
        composable("calendar"){ CalendarScreen(navController, userViewModel) }
        composable("formulaLearning"){ FormulaLearningScreen(navController, userViewModel) }
        composable("addFriends"){ AddFriendsScreen(navController, userViewModel) }
        composable("secondaryNews"){ SecondaryNewsScreen(navController, userViewModel) }
        composable("typeNews"){ TypeNewsScreen(navController, userViewModel) }
    }
}

