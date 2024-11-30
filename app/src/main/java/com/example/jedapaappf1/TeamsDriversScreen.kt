package com.example.jedapaappf1

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

@Composable
fun TeamsDriversScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel(), isTeams:Boolean){
    Box(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Teams & Drivers", showBackArrow = true, userViewModel = userViewModel)
            ////////////////////////////////////////////////////////////////////

            ///BODY///
            if (isTeams){
                TeamsBody(modifier = Modifier)
            }
            else{
                DriversBody(modifier = Modifier)
            }


        }
    }

}

@Composable
fun TeamsBody(modifier: Modifier){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Teams", color = Color.Black,
                modifier = Modifier.padding(vertical = 20.dp),
                fontSize = 25.sp, fontFamily = formula1Font
            )
            AddItem("RedBull", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Mercedes", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Ferrari", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Mclaren", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Aston Martin", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Haas", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("RB", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Williams", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Sauber", "mockup","LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Alpine", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")

        }
    }
}

@Composable
fun DriversBody(modifier: Modifier){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Drivers", color = Color.Black,
                modifier = Modifier.padding(vertical = 20.dp),
                fontSize = 25.sp, fontFamily = formula1Font
            )
            AddItem("Max Verstappen", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Sergio Pérez", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Charles Leclerc", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Carlos Sainz", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Lewis Hamilton", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("George Russell", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Fernando Alonso", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Lance Stroll", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Lando Norris", "mockup","LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Oscar Piastri", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Valtteri Bottas", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Zhou", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Esteban Ocon", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Pierre Gasly", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Nico Hulkenberg", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Kevin Magnussen", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Albon", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Colapinto", "mockup","LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Tsunoda", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")
            AddItem("Lawson", "mockup", "LoremIpsum akdfbkdasfbkasdfb akdfbksadfbhkajsdhbf akdfbhsadkfbsakdf akdfbsdkfbasdf kabdkf asdfkljbas fnkadjbf kadjbf")

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
        Image(
            painter = painterResource(R.drawable.mockup), ///CAMBIAR A LA IMAGEN DADA POR PARAMETRO
            contentDescription = "item",
            modifier = Modifier
        )
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