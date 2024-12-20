// Archivo: RegisterConfirmationScreen.kt
package com.example.jedapaappf1.screens.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel

@Composable
fun RegisterConfirmationScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFFE0E0E0))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp).padding(top = 30.dp)
                    .background(Color.White, shape = RoundedCornerShape(25.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(25.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de registro",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(250.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.marca_de_verificacion),
                    contentDescription = "Confirmación",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(250.dp).padding(top = 50.dp, bottom = 25.dp)
                )
                Text(
                    text = "Registration successful!",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Text(
                    text = "Your account was created successfully.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 0.dp)
                )
                Text(
                    text = "You can now log in.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 0.dp)
                )

                // Botón de continuar
                Button(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("register_confirmation") { inclusive = true }
                        }
                    },
                    modifier = Modifier.width(200.dp).padding(bottom = 20.dp, top = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Continue")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterConfirmationScreenPreview() {
    RegisterConfirmationScreen(navController = NavHostController(LocalContext.current))
}
