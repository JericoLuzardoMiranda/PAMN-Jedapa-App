// Archivo: HomeScreen.kt
package com.example.jedapaappf1.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jedapaappf1.navigation.MyHeader
import com.example.jedapaappf1.data.Noticia
import com.example.jedapaappf1.data.NoticiasList
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel

@Composable
fun HomeScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val image = painterResource(R.drawable.mockup)
    val noticias = listOf(
        Noticia(
            titulo = "Gran Premio de Abu Dhabi",
            descripcion = "El circuito de Yas Marina espera una carrera épica.",
            imagenRes = R.drawable.mockup
        ),
        Noticia(
            titulo = "Hamilton domina la clasificación",
            descripcion = "Lewis Hamilton toma la pole en un sábado emocionante.",
            imagenRes = R.drawable.mockup
        ),
        Noticia(
            titulo = "Red Bull y sus estrategias",
            descripcion = "Verstappen buscará cerrar la temporada con estilo.",
            imagenRes = R.drawable.mockup
        )
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Home", showBackArrow = false, userViewModel = userViewModel)
            ////////////////////////////////////////////////////////////////////

            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                            .clickable { navController.navigate("secondaryNews") }
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bienvenido al mundo de la Fórmula 1",
                        color = Color.Red, fontSize = 15.sp, fontFamily = formula1Font
                    )
                    Text(
                        text = "Disfruta las últimas noticias y eventos",
                        color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                }

                // Lista de noticias
                NoticiasList(
                    noticias = noticias,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}

