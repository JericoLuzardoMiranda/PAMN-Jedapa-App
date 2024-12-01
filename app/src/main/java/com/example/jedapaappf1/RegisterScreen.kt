package com.example.jedapaappf1

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

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

            // Formulario de registro
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var username by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var confirmPassword by remember { mutableStateOf("") }

                // Campo para el nombre de usuario
                TextField(
                    value = username,
                    onValueChange = { username = it
                        if (username.isNotEmpty()) usernameError = null
                    },
                    label = { Text("Nombre de usuario") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, top = 30.dp)
                )

                ErrorMessage(message = usernameError)

                // Campo para el email
                TextField(
                    value = email,
                    onValueChange = { email = it
                        if (email.isNotEmpty()) emailError = null
                    },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                )

                ErrorMessage(message = emailError)

                // Campo para la contraseña
                TextField(
                    value = password,
                    onValueChange = { password = it
                        if (password.isNotEmpty()) passwordError = null
                    },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                ErrorMessage(message = passwordError)

                // Campo para confirmar la contraseña
                TextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it
                        if (confirmPassword.isNotEmpty()) confirmPasswordError = null
                    },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                ErrorMessage(message = confirmPasswordError)

                // Botón de registrarse
                Button(
                    onClick = {
                        var isValid = true

                        if (username.isEmpty()) {
                            usernameError = "El nombre de usuario es obligatorio"
                            isValid = false
                        }

                        if (email.isEmpty()) {
                            emailError = "El correo electrónico es obligatorio"
                            isValid = false
                        } else if (!email.matches(emailRegex)) {
                            emailError = "Correo electrónico no válido"
                            isValid = false
                        }

                        if (password.isEmpty()) {
                            passwordError = "La contraseña es obligatoria"
                            isValid = false
                        } else if (password.length < 6) {
                            passwordError = "La contraseña debe tener al menos 6 caracteres"
                            isValid = false
                        }

                        if (confirmPassword.isEmpty() || confirmPassword != password) {
                            confirmPasswordError = "Las contraseñas no coinciden"
                            isValid = false
                        }

                        if (isValid) {
                            userViewModel.logIn()
                            userViewModel.registerUser(username, email, password)
                            println("Datos registrados: ${userViewModel.username}, ${userViewModel.email}")
                            navController.navigate("register_confirmation") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier.width(200.dp).padding(bottom = 20.dp, top = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Registrarse")
                }

                // Enlace para iniciar sesión
                Text(
                    text = "¿Ya eres uno de nosotros?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 0.dp)
                )

                TextButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        text = "Iniciar sesión",
                        color = Color(0xFF196CCE),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 0.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorMessage(message: String?) {
    if (message != null) {
        Text(
            text = message, color = Color.Red,
            fontSize = 16.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}
