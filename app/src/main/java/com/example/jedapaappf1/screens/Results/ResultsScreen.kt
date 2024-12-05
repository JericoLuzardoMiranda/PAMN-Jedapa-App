package com.example.jedapaappf1.screens.Results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jedapaappf1.data.DriverResult
import com.example.jedapaappf1.navigation.MyHeader
import com.example.jedapaappf1.R
import com.example.jedapaappf1.data.Result
import com.example.jedapaappf1.UserViewModel

@Composable
fun ResultsScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel(), isTeamsResults:Boolean) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val scrollState = rememberScrollState()
    val viewModel: ResultsViewModel = viewModel()
    val driverState by viewModel.driverState.observeAsState()
    LaunchedEffect(Unit){
        viewModel.getDrivers()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Results", showBackArrow = true, userViewModel = userViewModel)
            ////////////////////////////////////////////////////////////////////

            ///BODY///
            if (isTeamsResults){ TeamsResultsBody(modifier = Modifier) }
            else{ DriversResultsBody(modifier = Modifier, driverState) }



        }
    }
}

@Composable
fun DriversResultsBody(modifier: Modifier, driverState: Result?){
    val scrollState = rememberScrollState()
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val drivers = remember { mutableStateOf<List<DriverResult>>(listOf()) }
    val showErrorDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        //////////////////////////RESULTS TABLE/////////////////////////////
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp).verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row {
                Text(
                    text = "2023 DRIVERS RESULTS", color = Color.Black,
                    modifier = Modifier.padding(vertical = 20.dp),
                    fontSize = 25.sp, fontFamily = formula1Font
                )
            }

            Row (modifier = Modifier.wrapContentSize().fillMaxWidth()) {
                Spacer(modifier.padding(horizontal = 4.dp))
                Text("Pos", modifier = Modifier.padding(horizontal = 10.dp).width(30.dp), fontWeight = FontWeight.Bold)
                Text("Name", modifier = Modifier.padding(horizontal = 10.dp).width(120.dp), fontWeight = FontWeight.Bold)
                Text("Points", modifier = Modifier.padding(horizontal = 10.dp).width(50.dp), fontWeight = FontWeight.Bold)
                Text("Nationality", modifier = Modifier.padding(horizontal = 10.dp).width(100.dp), fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                when(driverState){
                    is Result.DriversResultsSuccess -> {
                        drivers.value = driverState.items
                    }
                    is Result.Error -> {
                        showErrorDialog.value = true
                    }
                    else -> {}
                }

                var counter = 1
                val sortedDrivers = drivers.value.sortedByDescending { it.points }
                for (driver in sortedDrivers){
                    AddRow(counter, driver.driver, driver.points, driver.nationality)
                    counter++
                }
            }
        }

    }
}

@Composable
fun TeamsResultsBody(modifier: Modifier){
    val scrollState = rememberScrollState()
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        //////////////////////////RESULTS TABLE/////////////////////////////
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp).verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row {
                Text(
                    text = "2023 TEAMS RESULTS", color = Color.Black,
                    modifier = Modifier.padding(vertical = 20.dp),
                    fontSize = 25.sp, fontFamily = formula1Font
                )
            }

            Row (modifier = Modifier.wrapContentSize().fillMaxWidth()) {
                Spacer(modifier.padding(horizontal = 4.dp))
                Text("Pos", modifier = Modifier.padding(horizontal = 10.dp).width(30.dp), fontWeight = FontWeight.Bold)
                Text("Name", modifier = Modifier.padding(horizontal = 10.dp).width(120.dp), fontWeight = FontWeight.Bold)
                Text("Points", modifier = Modifier.padding(horizontal = 10.dp).width(50.dp), fontWeight = FontWeight.Bold)
                Text("Drivers", modifier = Modifier.padding(horizontal = 10.dp).width(100.dp), fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                ///////////////////////INFORMACIÓN DE EJEMPLO/////////////////////////////
                AddRowTeams(1, "Red Bull", 800, "Max Verstappen, Sergio Pérez")
                AddRowTeams(2, "Mercedes", 409, "Lewis Hamilton, George Russell")
                AddRowTeams(3, "Ferrari", 406, "Carlos Sainz, Charles Leclerc")
                AddRowTeams(4, "McLaren", 342, "Lando Norris, Oscar Piastri")
                AddRowTeams(5, "Aston Martin", 342, "Fernando Alonso, Lance Stroll")
                AddRowTeams(6, "Red Bull", 800, "Max Verstappen, Sergio Pérez")
                AddRowTeams(7, "Mercedes", 409, "Lewis Hamilton, George Russell")
                AddRowTeams(8, "Ferrari", 406, "Carlos Sainz, Charles Leclerc")
                AddRowTeams(9, "McLaren", 342, "Lando Norris, Oscar Piastri")
                AddRowTeams(10, "Aston Martin", 342, "Fernando Alonso, Lance Stroll")
                AddRowTeams(11, "Red Bull", 800, "Max Verstappen, Sergio Pérez")
                AddRowTeams(12, "Mercedes", 409, "Lewis Hamilton, George Russell")
                AddRowTeams(13, "Ferrari", 406, "Carlos Sanz, Charles Leclerc")
                AddRowTeams(14, "McLaren", 342, "Lando Norris, Oscar Piastri")
                AddRowTeams(15, "Aston Martin", 342, "Fernando Alonso, Lance Stroll")
                AddRowTeams(16, "Red Bull", 800, "Max Verstappen, Sergio Pérez")
                AddRowTeams(17, "Mercedes", 409, "Lewis Hamilton, George Russell")
                AddRowTeams(18, "Ferrari", 406, "Carlos Sainz, Charles Leclerc")
                AddRowTeams(19, "McLaren", 342, "Lando Norris, Oscar Piastri")
                AddRowTeams(20, "Aston Martin", 342, "Fernando Alonso, Lance Stroll")

            }
        }

    }
}


@Composable
fun AddRow(position: Int, name:String, points: Int, nationality:String){
    val rowColor = when {
        position == 1 -> Color(0xFFF2D45C) // Amarillo
        position % 2 == 0 -> Color(0x7AE0E0E0) // Gris claro
        else -> Color(0xFFD7E0F7) // Azul claro
    }
    Row (modifier = Modifier.background(color=rowColor).padding(vertical = 7.dp).wrapContentSize().fillMaxWidth()){
        Text("$position", modifier = Modifier.padding(horizontal = 10.dp).width(30.dp))
        Text(name, modifier = Modifier.padding(horizontal = 10.dp).width(120.dp))
        Text("$points", modifier = Modifier.padding(horizontal = 10.dp).width(50.dp))
        Text(nationality, modifier = Modifier.padding(horizontal = 10.dp).width(100.dp))
    }
}

@Composable
fun AddRowTeams(position: Int, name:String, points: Int, drivers:String){
    val rowColor = when {
        position == 1 -> Color(0xFFF2D45C) // Amarillo
        position % 2 == 0 -> Color(0x7AE0E0E0) // Gris claro
        else -> Color(0xFFD7E0F7) // Azul claro
    }
    Row (modifier = Modifier.background(color=rowColor).padding(vertical = 7.dp).wrapContentSize().fillMaxWidth()){
        Text("$position", modifier = Modifier.padding(horizontal = 10.dp).width(20.dp))
        Text(name, modifier = Modifier.padding(horizontal = 10.dp).width(130.dp))
        Text("$points", modifier = Modifier.padding(horizontal = 10.dp).width(30.dp))
        Text(drivers, modifier = Modifier.padding(horizontal = 10.dp).width(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    val drivers = listOf(
        DriverResult("1", "Pepe", 123, "Spanish"),
        DriverResult("2", "Pepe", 123, "Spanish"),
        DriverResult("3", "Pepe", 123, "Spanish")
    )
    DriversResultsBody(modifier = Modifier, driverState = Result.DriversResultsSuccess(drivers))
}