package com.example.jedapaappf1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ResultsScreen(navController: NavHostController) {


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Results", showBackArrow = true)
            ////////////////////////////////////////////////////////////////////

            //////////////////////////RESULTS TABLE/////////////////////////////
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row {
                    Text("2023 DRIVERS RESULTS",
                        modifier = Modifier.padding(vertical = 20.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                        )
                }

                Row (modifier = Modifier.wrapContentSize().fillMaxWidth()) {
                    Text("Position", modifier = Modifier.padding(horizontal = 10.dp).width(70.dp), fontWeight = FontWeight.Bold)
                    Text("Name", modifier = Modifier.padding(horizontal = 10.dp).width(80.dp), fontWeight = FontWeight.Bold)
                    Text("Points", modifier = Modifier.padding(horizontal = 10.dp).width(40.dp), fontWeight = FontWeight.Bold)
                    Text("Nationality", modifier = Modifier.padding(horizontal = 10.dp).width(80.dp), fontWeight = FontWeight.Bold)
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
}
@Composable
fun AddRow(position: Int, name:String, points: Int, nationality:String){
    val rowColor = when {
        position == 1 -> Color(0xFFF2D45C) // Amarillo
        position % 2 == 0 -> Color(0x7AE0E0E0) // Gris claro
        else -> Color(0xFFD7E0F7) // Azul claro
    }
    Row (modifier = Modifier.background(color=rowColor).padding(vertical = 7.dp).wrapContentSize().fillMaxWidth()){
        Text("$position", modifier = Modifier.padding(horizontal = 10.dp).width(20.dp))
        Text(name, modifier = Modifier.padding(horizontal = 10.dp).width(130.dp))
        Text("$points", modifier = Modifier.padding(horizontal = 10.dp).width(30.dp))
        Text(nationality, modifier = Modifier.padding(horizontal = 10.dp).width(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    ResultsScreen(navController = NavHostController(LocalContext.current))
}