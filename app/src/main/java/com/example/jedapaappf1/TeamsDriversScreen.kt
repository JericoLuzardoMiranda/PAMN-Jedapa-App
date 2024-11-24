package com.example.jedapaappf1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun TeamsDriversScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Teams & Drivers", showBackArrow = true, userViewModel = userViewModel)
            ////////////////////////////////////////////////////////////////////

            Text("Teams/Drivers Page")

        }
    }

}

@Preview(showBackground = true)
@Composable
fun TeamsDriversScreenPreview() {
    TeamsDriversScreen(navController = NavHostController(LocalContext.current))
}