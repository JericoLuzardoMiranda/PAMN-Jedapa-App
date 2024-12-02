package com.example.jedapaappf1

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ResultsScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel(), isTeamsResults:Boolean) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val scrollState = rememberScrollState()
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
            else{ DriversResultsBody(modifier = Modifier) }



        }
    }
}

@Composable
fun DriversResultsBody(modifier: Modifier){
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

                ///////////////////////INFORMACIÓN DE EJEMPLO/////////////////////////////
                AddRow(1, "Max Verstappen", 342, "Netherlands")
                AddRow(2, "Sergio Pérez", 342, "Mexico")
                AddRow(3, "Fernando Alonso", 342, "Spain")
                AddRow(4, "Lance Stroll", 342, "Canada")
                AddRow(5, "Carlos Sainz", 342, "Spain")
                AddRow(6, "Charles Leclerc", 342, "Monaco")
                AddRow(7, "Max Verstappen", 342, "Dutch")
                AddRow(8, "Max Verstappen", 342, "Dutch")
                AddRow(9, "Max Verstappen", 342, "Dutch")
                AddRow(10,"Max Verstappen", 342, "Dutch")
                AddRow(11, "Max Verstappen", 342, "Dutch")
                AddRow(12, "Max Verstappen", 342, "Dutch")
                AddRow(13, "Max Verstappen", 342, "Dutch")
                AddRow(14, "Max Verstappen", 342, "Dutch")
                AddRow(15, "Max Verstappen", 342, "Dutch")
                AddRow(16, "Max Verstappen", 342, "Dutch")
                AddRow(17, "Max Verstappen", 342, "Dutch")
                AddRow(18, "Max Verstappen", 342, "Dutch")
                AddRow(19, "Max Verstappen", 342, "Dutch")
                AddRow(20, "Max Verstappen", 342, "Dutch")
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
    ResultsScreen(navController = NavHostController(LocalContext.current), isTeamsResults = true)
}