// Archivo: TypeNewsScreen.kt
package com.example.jedapaappf1.screens.TypeNews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.example.jedapaappf1.navigation.MyHeader
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel

@Composable
fun TypeNewsScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val image = painterResource(R.drawable.mockup)
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(
                navController = navController, currentScreen = "News",
                showBackArrow = true, userViewModel = userViewModel
            )
            ////////////////////////////////////////////////////////////////////

            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp).verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Primera noticia
                    Image(
                        painter = image,
                        contentDescription = null, contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(200.dp)
                    )

                    // Cuadro de texto por debajo de la imagen
                    Box(
                        modifier = Modifier
                            .fillMaxWidth().background(Color(0xFF0F969C))
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "News 1", color = Color.Black,
                            fontSize = 25.sp, fontFamily = formula1Font,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Segunda noticia
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(200.dp).padding(top = 30.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth().background(Color(0xFF0F969C))
                            .padding(8.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "News 2", color = Color.Black,
                            fontSize = 25.sp, fontFamily = formula1Font,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Tercera noticia
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(200.dp).padding(top = 30.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth().background(Color(0xFF0F969C))
                            .padding(8.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "News 3", color = Color.Black,
                            fontSize = 25.sp, fontFamily = formula1Font,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Cuarta noticia
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(200.dp).padding(top = 30.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth().background(Color(0xFF0F969C))
                            .padding(8.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "News 4", color = Color.Black,
                            fontSize = 25.sp, fontFamily = formula1Font,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Quinta imagen
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(200.dp).padding(top = 30.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth().background(Color(0xFF0F969C))
                            .padding(8.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "News 5", color = Color.Black,
                            fontSize = 25.sp, fontFamily = formula1Font,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TypeNewsScreenPreview() {
    TypeNewsScreen(navController = NavHostController(LocalContext.current))
}

