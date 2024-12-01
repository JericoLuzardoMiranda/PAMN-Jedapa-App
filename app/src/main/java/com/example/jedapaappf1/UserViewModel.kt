package com.example.jedapaappf1

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    // Estado que indica si el usuario está logueado
    private val _isUserLoggedIn = mutableStateOf(false)
    val isUserLoggedIn: Boolean get() = _isUserLoggedIn.value

    // Almacenar los datos del usuario
    var username by mutableStateOf<String?>(null)
    var email by mutableStateOf<String?>(null)
    var password by mutableStateOf<String?>(null)

    // Función para iniciar sesión
    fun logIn() { _isUserLoggedIn.value = true }

    // Función para cerrar sesión
    fun logOut() { _isUserLoggedIn.value = false }

    // Guardamos los datos del usuario
    fun registerUser(username: String, email: String, password: String) {
        this.username = username
        this.email = email
        this.password = password
        println("User registered: Username = $username, Email = $email, Password = $password")
    }

}