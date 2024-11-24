package com.example.jedapaappf1

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    // Estado que indica si el usuario está logueado
    private val _isUserLoggedIn = mutableStateOf(false)
    val isUserLoggedIn: Boolean
        get() = _isUserLoggedIn.value

    // Función para loguear al usuario
    fun logIn() {
        _isUserLoggedIn.value = true
    }

    // Función para hacer logout (si es necesario)
    fun logOut() {
        _isUserLoggedIn.value = false
    }
}
