// Archivo: SecondaryNewsScreen.kt
package com.example.jedapaappf1.screens.SecondaryNews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
fun SecondaryNewsScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val image = painterResource(R.drawable.mockup)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(
                navController = navController, currentScreen = "Secondary News",
                showBackArrow = true, userViewModel = userViewModel
            )
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
                        painter = image, contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Titular de la Noticia 1",
                        color = Color.Red, fontSize = 25.sp, fontFamily = formula1Font,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit , sed do eiusmod",
                        color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                                " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                                "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea " +
                                "commodo consequat.",
                        color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                                " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                                "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea " +
                                "commodo consequat.",
                        color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(bottom = 18.dp)
                    )

                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.width(300.dp).height(150.dp).padding(bottom = 18.dp)
                    )

                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                                " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                                "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea " +
                                "commodo consequat.",
                        color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Most Read News", color = Color.Red,
                        fontSize = 25.sp, fontFamily = formula1Font,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.weight(1f).padding(4.dp).height(100.dp)
                        )

                        Image(
                            painter = image, contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.weight(1f).padding(4.dp).height(100.dp)
                        )

                        Image(
                            painter = image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.weight(1f).padding(4.dp).height(100.dp)
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondaryNewsScreenPreview() {
    SecondaryNewsScreen(navController = NavHostController(LocalContext.current))
}

