package com.example.jedapaappf1

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

data class Circuito(
    val nombre: String,
    val imagenId: Int,
    val datosFechasHoras: List<Triple<String, String, String>>
)

@Composable
fun CalendarScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))

    val circuitos = listOf(
        Circuito("Circuito de Bahrain", R.drawable.bahrain_circuit, listOf(
            Triple("race", "02/03/2024", "15:00"),
            Triple("qualifying", "01/03/2024", "14:00"),
            Triple("practice3", "01/03/2024", "11:00"),
            Triple("practice2", "28/02/2024", "15:30"),
            Triple("practice1", "28/02/2024", "11:00")
        )),
        Circuito("Circuito de Catalunya", R.drawable.catalunya_circuit, listOf(
            Triple("race", "23/06/2024", "14:00"),
            Triple("qualifying", "22/06/2024", "15:00"),
            Triple("practice3", "22/06/2024", "11:30"),
            Triple("practice2", "21/06/2024", "16:00"),
            Triple("practice1", "21/06/2024", "12:30")
        )),
        Circuito("Circuito de Hungaroring", R.drawable.hungaroring_circuit, listOf(
            Triple("race", "21/07/2024", "14:00"),
            Triple("qualifying", "20/07/2024", "15:00"),
            Triple("practice3", "20/07/2024", "11:30"),
            Triple("practice2", "19/07/2024", "16:00"),
            Triple("practice1", "19/07/2024", "12:30")
        )),
        Circuito("Circuito de Imola", R.drawable.imola_circuit, listOf(
            Triple("race", "19/05/2024", "14:00"),
            Triple("qualifying", "18/05/2024", "15:00"),
            Triple("practice3", "18/05/2024", "11:30"),
            Triple("practice2", "17/05/2024", "16:00"),
            Triple("practice1", "17/05/2024", "12:30")
        )),
        Circuito("Circuito de Mónaco", R.drawable.monaco_circuit, listOf(
            Triple("race", "26/05/2024", "14:00"),
            Triple("qualifying", "25/05/2024", "15:00"),
            Triple("practice3", "25/05/2024", "11:30"),
            Triple("practice2", "24/05/2024", "16:00"),
            Triple("practice1", "24/05/2024", "12:30")
        )),
        Circuito("Circuito de Monza", R.drawable.monza_circuit, listOf(
            Triple("race", "01/09/2024", "14:00"),
            Triple("qualifying", "31/08/2024", "15:00"),
            Triple("practice3", "31/08/2024", "11:30"),
            Triple("practice2", "30/08/2024", "16:00"),
            Triple("practice1", "30/08/2024", "12:30")
        )),
        Circuito("Circuito de Mugello", R.drawable.mugello_circuit, listOf(
            Triple("race", "13/09/2024", "15:10"),
            Triple("qualifying", "12/09/2024", "15:00"),
            Triple("practice3", "12/09/2024", "12:00"),
            Triple("practice2", "11/09/2024", "15:00"),
            Triple("practice1", "11/09/2024", "11:00")
        )),
        Circuito("Circuito de Portimao", R.drawable.portimao_circuit, listOf(
            Triple("race", "02/05/2024", "16:00"),
            Triple("qualifying", "01/05/2024", "16:00"),
            Triple("practice3", "01/05/2024", "13:00"),
            Triple("practice2", "30/04/2024", "16:00"),
            Triple("practice1", "30/04/2024", "12:30")
        )),
        Circuito("Circuito de Red Bull Ring", R.drawable.red_bull_ring_circuit, listOf(
            Triple("race", "30/06/2024", "14:00"),
            Triple("qualifying", "29/06/2024", "15:00"),
            Triple("practice3", "29/06/2024", "11:00"),
            Triple("practice2", "28/06/2024", "15:30"),
            Triple("practice1", "28/06/2024", "11:30")
        )),
        Circuito("Circuito de Silverstone", R.drawable.silverstone_circuit, listOf(
            Triple("race", "07/07/2024", "15:00"),
            Triple("qualifying", "06/07/2024", "15:00"),
            Triple("practice3", "06/07/2024", "11:30"),
            Triple("practice2", "05/07/2024", "16:00"),
            Triple("practice1", "05/07/2024", "12:30")
        )),
        Circuito("Circuito de Sochi", R.drawable.sochi_circuit, listOf(
            Triple("race", "26/09/2024", "14:00"),
            Triple("qualifying", "25/09/2024", "14:00"),
            Triple("practice3", "25/09/2024", "11:00"),
            Triple("practice2", "24/09/2024", "14:00"),
            Triple("practice1", "24/09/2024", "10:30")
        )),
        Circuito("Circuito de Spa-Francorchamps", R.drawable.spa_francorchamps_circuit, listOf(
            Triple("race", "28/07/2024", "14:00"),
            Triple("qualifying", "27/07/2024", "15:00"),
            Triple("practice3", "27/07/2024", "11:30"),
            Triple("practice2", "26/07/2024", "16:00"),
            Triple("practice1", "26/07/2024", "12:30")
        )),
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.calendar_background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize().padding(top = 90.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ///////////////////////////// HEADER /////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Calendar",
                showBackArrow = true, userViewModel = userViewModel)
            /////////////////////////////////////////////////////////////////////

            Text(
                text = "Calendar", color = Color.Black,
                fontSize = 40.sp, fontFamily = formula1Font,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) { items(circuitos) { circuito -> CircuitoItem(circuito = circuito) } }
        }
    }
}

@Composable
fun CircuitoItem(circuito: Circuito) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    var isFlipped by remember { mutableStateOf(false) }

    // Animación de rotación
    val rotation by animateFloatAsState(targetValue = if (isFlipped) 180f else 0f)
    val frontAlpha = if (rotation <= 90f) 1f else 0f
    val backAlpha = if (rotation > 90f) 1f else 0f
    val textRotation = if (isFlipped) 180f else 0f

    Box(
        modifier = Modifier.padding(8.dp).width(300.dp).height(200.dp)
            .clickable { isFlipped = !isFlipped }
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            },
        contentAlignment = Alignment.Center
    ) {
        // Lado frontal
        if (frontAlpha > 0f) {
            Image(
                painter = painterResource(id = circuito.imagenId),
                contentDescription = circuito.nombre,
                modifier = Modifier.fillMaxSize().alpha(frontAlpha),
                contentScale = ContentScale.Crop
            )
        }

        // Lado posterior
        if (backAlpha > 0f) {
            Column(
                modifier = Modifier
                    .fillMaxSize().graphicsLayer { rotationY = textRotation }
                    .alpha(backAlpha).padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título del circuito
                Text(
                    text = circuito.nombre,
                    fontSize = 18.sp, fontFamily = formula1Font,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // Lista de eventos, fechas y horas
                circuito.datosFechasHoras.forEach { (evento, fecha, hora) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Columna para el evento
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = evento, fontSize = 14.sp,
                                color = Color.Black, textAlign = TextAlign.Center
                            )
                        }

                        // Columna para la fecha
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "$fecha", fontSize = 14.sp,
                                color = Color.Black, textAlign = TextAlign.Center
                            )
                        }

                        // Columna para la hora
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "$hora", fontSize = 14.sp,
                                color = Color.Black, textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen(navController = NavHostController(LocalContext.current))
}
