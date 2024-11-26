package com.example.jedapaappf1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun MyHeader(navController: NavHostController, currentScreen: String, showBackArrow: Boolean = true, userViewModel: UserViewModel = viewModel()){
    val isUserLoggedIn = userViewModel.isUserLoggedIn

    val onSignUpClick = { navController.navigate("signup") }
    val onLoginClick = { navController.navigate("login") }
    val onUserProfileClick = { navController.navigate("user_profile") }
    val onLogOutClick = {
        userViewModel.logOut()
        navController.navigate("home")  // Redirigir al Home después de cerrar sesión
    }

    val image_logo = painterResource(R.drawable.logo)
    val formula1Font = FontFamily(Font(R.font.formula1_bold))

    // Estado para controlar la visibilidad del menú
    val isMenuExpanded = remember { mutableStateOf(false) }
    val isResultsExpanded = remember { mutableStateOf(false) }
    val isNewsExpanded = remember { mutableStateOf(false) }
    val isUserMenuExpanded = remember { mutableStateOf(false) }

    // Primera fila
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = image_logo,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(120.dp).padding(start = 20.dp)
        )

        if (!isUserLoggedIn) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onSignUpClick() },
                    modifier = Modifier
                        .padding(5.dp)
                        .height(38.dp),
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
        } else {
            // Si el usuario está logueado, mostramos el icono de usuario con menú desplegable
            Column {
                Image(
                    painter = painterResource(R.drawable.user_icon),
                    contentDescription = "User Profile",
                    modifier = Modifier
                        .clickable { isUserMenuExpanded.value = true }
                        .padding(end = 16.dp)
                        .size(30.dp)
                )

                // DropdownMenu para usuario logueado
                DropdownMenu(
                    expanded = isUserMenuExpanded.value,
                    onDismissRequest = { isUserMenuExpanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Profile") },
                        onClick = {
                            isUserMenuExpanded.value = false
                            onUserProfileClick()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Add friends") },
                        onClick = {
                            isUserMenuExpanded.value = false
                            navController.navigate("addFriends")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Log out") },
                        onClick = {
                            isUserMenuExpanded.value = false
                            onLogOutClick()
                        }
                    )
                }
            }
        }

    }

    // Segunda fila
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF294D61))
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            if (showBackArrow) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .padding(end = 8.dp)
                        .width(24.dp)
                        .height(24.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = currentScreen,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = formula1Font,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Text(
            text = "Menu",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = formula1Font,
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
                    Text("Interviews", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF), modifier = Modifier.clickable { navController.navigate("typeNews") })
                    Text("Race summaries", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                    Text("Teams and Drivers News", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                    Text("Games", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF))
                }
            }

            Text("Calendar", fontSize = 16.sp, fontFamily = formula1Font, color = Color(0xFFFFFFFF), modifier = Modifier.clickable { navController.navigate("calendar") })

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
                    Text("Teams", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF), modifier = Modifier.clickable { navController.navigate("teamsResults") })
                    Text("Drivers", fontSize = 14.sp, fontFamily = formula1Font, color = Color(0xFFDFDFDF), modifier = Modifier.clickable { navController.navigate("driversResults") })
                }
            }

            Text("Teams", fontSize = 16.sp, fontFamily = formula1Font, color = Color(0xFFFFFFFF), modifier = Modifier.clickable { navController.navigate("teams") })
            Text("Drivers", fontSize = 16.sp, fontFamily = formula1Font, color = Color(0xFFFFFFFF), modifier = Modifier.clickable { navController.navigate("drivers") })
            Text("Formula Learning", fontSize = 16.sp, fontFamily = formula1Font, color = Color(0xFFFFFFFF), modifier = Modifier.clickable { navController.navigate("formulaLearning") })
        }
    }
}
