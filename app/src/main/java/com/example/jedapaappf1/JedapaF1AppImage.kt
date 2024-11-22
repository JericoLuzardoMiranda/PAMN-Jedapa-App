// Archivo: JedapaF1AppImage.kt
package com.example.jedapaappf1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight

@Composable
fun JedapaF1AppImage(name: String, modifier: Modifier = Modifier, onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {

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
            MyHeader(modifier = Modifier, onSignUpClick, onLoginClick)
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
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bienvenido al mundo de la Fórmula 1",
                        color = Color.Red, fontSize = 15.sp, fontWeight = FontWeight.Bold
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
