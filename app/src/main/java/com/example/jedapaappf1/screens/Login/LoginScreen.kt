// Archivo: LoginScreen.kt
package com.example.jedapaappf1.screens.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jedapaappf1.R

@Composable
fun LoginScreen(navController: NavHostController) {
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

            // Formulario de inicio sesión
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                // Campo para el nombre de usuario
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nombre de usuario") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, top = 30.dp)
                )

                // Campo para la contraseña
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                // Botón de iniciar sesión
                Button(
                    onClick = {
                        navController.navigate("home")
                    },
                    modifier = Modifier.width(200.dp).padding(bottom = 20.dp, top = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Iniciar sesión")
                }

                // Enlace para registrarse
                Text(
                    text = "¿No tienes cuenta?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 0.dp)
                )

                Text(
                    text = "Descubre lo que estás perdiendo",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 0.dp)
                )

                TextButton(
                    onClick = {
                        navController.navigate("signup")
                    },
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        text = "Regístrate",
                        color = Color(0xFF196CCE),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 0.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun LoginPreview() {
    LoginScreen(navController = NavHostController(LocalContext.current))
}
