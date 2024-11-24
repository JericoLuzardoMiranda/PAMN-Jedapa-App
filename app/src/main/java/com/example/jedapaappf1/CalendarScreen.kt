package com.example.jedapaappf1

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class Circuito(val nombre: String, val imagenId: Int)

@Composable
fun CalendarScreen(navController: NavHostController) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))

    val circuitos = listOf(
        Circuito("Circuito de Bahrain", R.drawable.bahrain_circuit),
        Circuito("Circuito de Catalunya", R.drawable.catalunya_circuit),
        Circuito("Circuito de Hungaroring", R.drawable.hungaroring_circuit),
        Circuito("Circuito de Imola", R.drawable.imola_circuit),
        Circuito("Circuito de Mónaco", R.drawable.monaco_circuit),
        Circuito("Circuito de Monza", R.drawable.monza_circuit),
        Circuito("Circuito de Mugello", R.drawable.mugello_circuit),
        Circuito("Circuito de Portimao", R.drawable.portimao_circuit),
        Circuito("Circuito de Red Bull Ring", R.drawable.red_bull_ring_circuit),
        Circuito("Circuito de Silverstone", R.drawable.silverstone_circuit),
        Circuito("Circuito de Sochi", R.drawable.sochi_circuit),
        Circuito("Circuito de Spa-Francorchamps", R.drawable.spa_francorchamps_circuit),
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.calendar_background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize().padding(top = 100.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Calendar", showBackArrow = true)
            ////////////////////////////////////////////////////////////////////

            Text(
                text = "Calendar", color = Color.Black,
                fontSize = 40.sp, fontFamily = formula1Font,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(circuitos) { circuito -> CircuitoItem(circuito = circuito) }
            }
        }
    }
}

@Composable
fun CircuitoItem(circuito: Circuito) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp).border(2.dp, Color.Black)
        ) {
            Image(
                painter = painterResource(id = circuito.imagenId),
                contentDescription = circuito.nombre,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = circuito.nombre, fontSize = 18.sp, color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen(navController = NavHostController(LocalContext.current))
}
