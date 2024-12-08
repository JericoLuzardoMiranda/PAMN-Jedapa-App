package com.example.jedapaappf1.screens.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel

@Composable
fun RegisterScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
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
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, top = 30.dp)
                )

                ErrorMessage(message = usernameError)

                // Campo para el email
                TextField(
                    value = email,
                    onValueChange = { email = it
                        if (email.isNotEmpty()) emailError = null
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                )

                ErrorMessage(message = emailError)

                // Campo para la contraseña
                TextField(
                    value = password,
                    onValueChange = { password = it
                        if (password.isNotEmpty()) passwordError = null
                    },
                    label = { Text("Password") },
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
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                ErrorMessage(message = confirmPasswordError)

                // Botón de registrarse
                Button(
                    onClick = {
                        var isValid = true

                        if (username.isEmpty()) {
                            usernameError = "Username is required."
                            isValid = false
                        }

                        if (email.isEmpty()) {
                            emailError = "Email is required."
                            isValid = false
                        } else if (!email.matches(emailRegex)) {
                            emailError = "Invalid email."
                            isValid = false
                        }

                        if (password.isEmpty()) {
                            passwordError = "Password is required."
                            isValid = false
                        } else if (password.length < 6) {
                            passwordError = "Password must be at least 6 characters long."
                            isValid = false
                        }

                        if (confirmPassword.isEmpty() || confirmPassword != password) {
                            confirmPasswordError = "Passwords do not match."
                            isValid = false
                        }

                        if (isValid) {
                            userViewModel.registerUser(username, email, password) { success ->
                                if (success) {
                                    println("Data registered succesfully")
                                    navController.navigate("register_confirmation") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                } else { confirmPassword = "Error registering user." }
                            }
                        }
                    },
                    modifier = Modifier.width(200.dp).padding(bottom = 20.dp, top = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Sign up")
                }

                // Enlace para iniciar sesión
                Text(
                    text = "Are you one of us yet?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 0.dp)
                )

                TextButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        text = "Log in",
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
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.error_icon),
                contentDescription = "Error", tint = Color.Red,
                modifier = Modifier.size(20.dp).padding(end = 8.dp)
            )
            Text(
                text = message, color = Color.Red,
                fontSize = 14.sp, fontWeight = FontWeight.SemiBold
            )
        }
    }
}
