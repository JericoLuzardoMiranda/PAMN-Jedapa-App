package com.example.jedapaappf1.screens.TeamsDrivers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jedapaappf1.navigation.MyHeader
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel
import com.example.jedapaappf1.data.Driver
import com.example.jedapaappf1.data.Result
import com.example.jedapaappf1.data.Team

@Composable
fun TeamsDriversScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel(), isTeams:Boolean){
    val viewModel: TeamsDriversViewModel = viewModel()
    val driverState by viewModel.driverState.observeAsState()
    val teamState by viewModel.teamState.observeAsState()
    LaunchedEffect(Unit){
        viewModel.getDrivers()
        viewModel.getTeams()
    }
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

            /////////////////////////////BODY//////////////////////////////////
            if (isTeams){ TeamsBody(modifier = Modifier, teamState) }
            else{ DriversBody(modifier = Modifier, driverState) }
            //////////////////////////////////////////////////////////////////
        }
    }

}

@Composable
fun TeamsBody(modifier: Modifier, teamState: Result?){
    val scrollState = rememberScrollState()
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val teams = remember { mutableStateOf<List<Team>>(listOf()) }
    val showErrorDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Teams", color = Color.Black,
                modifier = Modifier.padding(vertical = 20.dp),
                fontSize = 25.sp, fontFamily = formula1Font
            )
            when(teamState){
                is Result.TeamsSuccess -> {
                    teams.value = teamState.items
                }
                is Result.Error -> {
                    showErrorDialog.value = true
                }
                else -> {}
            }

            for (team in teams.value){
                AddItem(team.name, team.imageRef, team.description)
            }
        }
    }
}

@Composable
fun DriversBody(modifier: Modifier, driverState: Result?){
    val scrollState = rememberScrollState()
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val drivers = remember { mutableStateOf<List<Driver>>(listOf()) }
    val showErrorDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Drivers", color = Color.Black,
                modifier = Modifier.padding(vertical = 20.dp),
                fontSize = 25.sp, fontFamily = formula1Font
            )
            when(driverState){
                is Result.DriversSuccess -> {
                    drivers.value = driverState.items
                }
                is Result.Error -> {
                    showErrorDialog.value = true
                }
                else -> {}
            }

            for (driver in drivers.value){
                AddItem(driver.name, driver.imageRef, driver.description)
            }
        }
    }
}

@Composable
fun AddItem(name:String, image:String, description:String){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val isItemExpanded = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(vertical = 20.dp)
            .background(color = Color(0xFFD7E7F7))
            .width(250.dp)
            .padding(horizontal = 20.dp)
            .padding(vertical = 20.dp)
            .clickable { isItemExpanded.value = true },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val context = LocalContext.current
        val imageResId = context.resources.getIdentifier(image, "drawable", context.packageName)
        if (imageResId != 0) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "item",
                modifier = Modifier
            )
        }
        else {
            // Mockup en caso de no encontrar la imagen
            Image(
                painter = painterResource(id = R.drawable.mockup),
                contentDescription = "item no encontrado",
                modifier = Modifier
            )
        }
        Text(
            text=name,
            fontSize = 15.sp, fontFamily = formula1Font,
            modifier = Modifier.padding(vertical=10.dp)
        )
        if (isItemExpanded.value){
            Text(
                text=description,
                fontSize = 10.sp, fontFamily = formula1Font,
                modifier = Modifier.padding(vertical=10.dp)
                    .clickable { isItemExpanded.value = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamsDriversScreenPreview() {
    TeamsDriversScreen(navController = NavHostController(LocalContext.current), isTeams = true)
}