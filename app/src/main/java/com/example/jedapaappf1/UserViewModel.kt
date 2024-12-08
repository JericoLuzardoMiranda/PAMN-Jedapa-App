package com.example.jedapaappf1

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    // Estado de autenticación
    private val _isUserLoggedIn = mutableStateOf(false)
    val isUserLoggedIn: Boolean get() = _isUserLoggedIn.value

    // Almacenar los datos del usuario
    var username by mutableStateOf<String?>(null)
    var email by mutableStateOf<String?>(null)
    var password by mutableStateOf<String?>(null)

    // Función para iniciar sesión
    fun logIn(email: String, password: String, onComplete: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isUserLoggedIn.value = true
                    val user = firebaseAuth.currentUser
                    username = user?.displayName
                    this.email = user?.email
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }

    // Función para cerrar sesión
    fun logOut() {
        firebaseAuth.signOut()
        _isUserLoggedIn.value = false
    }

    // Función para registrar usuario
    fun registerUser(username: String, email: String, password: String, onComplete: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.updateProfile(
                        UserProfileChangeRequest.Builder().setDisplayName(username).build())
                    _isUserLoggedIn.value = true
                    this.username = username
                    this.email = email
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }
}
