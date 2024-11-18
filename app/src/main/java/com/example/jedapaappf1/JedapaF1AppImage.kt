// Archivo: JedapaF1AppImage.kt
package com.example.jedapaappf1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jedapaappf1.ui.theme.JedapaAppF1Theme

@Composable
fun JedapaF1AppImage(name: String, modifier: Modifier = Modifier, onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val image = painterResource(R.drawable.mockup)
    val image_logo = painterResource(R.drawable.logo)
    val image_logo_F1 = painterResource(R.drawable.logo_f1)
    val formula1Font = FontFamily(Font(R.font.formula1_bold))

    // Estado para controlar la visibilidad del menú
    val isMenuExpanded = remember { mutableStateOf(false) }
    val isResultsExpanded = remember { mutableStateOf(false) }
    val isNewsExpanded = remember { mutableStateOf(false) }

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
            // Primera fila
            Row(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = image_logo,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.width(120.dp).padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.width(60.dp))

                Button(
                    onClick = { onSignUpClick() },
                    modifier = Modifier.padding(5.dp).height(38.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD3D3D3),
                        contentColor = Color(0xFF808080)
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("Sign Up")
                }

                Button(
                    onClick = { onLoginClick() },
                    modifier = Modifier
                        .padding(5.dp)
                        .height(38.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD7E7F7),
                        contentColor = Color(0xFF0F969C),
                    ),
                    border = BorderStroke(1.dp, Color(0xFF0F969C)),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("Log in")
                }
            }

            // Segunda fila
            Row(
                modifier = Modifier.fillMaxWidth().background(Color(0xFF294D61))
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = image_logo_F1,
                    contentDescription = null,
                    modifier = Modifier.width(55.dp)
                )

                Text(
                    text = "Menu", color = Color.White,
                    fontSize = 18.sp, fontFamily = formula1Font,
                    modifier = Modifier.clickable {
                        isMenuExpanded.value = !isMenuExpanded.value
                    }
                )
            }

            // Mostrar el menú desplegable si está expandido
            if (isMenuExpanded.value) {
                Column(
                    modifier = Modifier.fillMaxWidth().background(Color(0xFF6DA5C0))
                        .padding(16.dp)
                ) {

                    Text(
                        text = "News",
                        fontSize = 16.sp,
                        fontFamily = formula1Font,
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier.clickable {
                            isNewsExpanded.value = !isNewsExpanded.value
                        }
                    )

                    if (isNewsExpanded.value) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
                        ) {
                            Text("Interviews", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                            Text("Race summaries", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                            Text("Teams and Drivers News", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                            Text("Games", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                        }
                    }

                    Text("Calendar", fontSize = 16.sp, fontFamily = formula1Font, color = Color(0xFFFFFFFF))

                    Text(
                        text = "Results", fontSize = 16.sp, fontFamily = formula1Font,
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier.clickable {
                            isResultsExpanded.value = !isResultsExpanded.value
                        }
                    )

                    if (isResultsExpanded.value) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                        ) {
                            Text("Teams", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                            Text("Drivers", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                        }
                    }

                    Text("Teams", fontSize = 16.sp, fontFamily = formula1Font, color = Color(0xFFFFFFFF))
                    Text("Drivers", fontSize = 16.sp, fontFamily = formula1Font, color = Color(0xFFFFFFFF))
                    Text("Formula Learning", fontSize = 16.sp, fontFamily = formula1Font, color = Color(0xFFFFFFFF))
                }
            }

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
