// Archivo: HomeScreen.kt
package com.example.jedapaappf1.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jedapaappf1.navigation.MyHeader
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel
import com.example.jedapaappf1.data.News
import com.example.jedapaappf1.data.Result

@Composable
fun HomeScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val viewModel: HomeViewModel = viewModel()
    val homeState by viewModel.homeState.observeAsState()
    LaunchedEffect(Unit){
        viewModel.getNews()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Home", showBackArrow = false, userViewModel = userViewModel)
            ////////////////////////////////////////////////////////////////////

            HomeBody(modifier = Modifier, homeState=homeState, navController)

        }
    }
}

@Composable
fun HomeBody(modifier: Modifier, homeState: Result?, navController: NavHostController){
    val news = remember { mutableStateOf<List<News>>(listOf()) }
    val showErrorDialog = remember { mutableStateOf(false) }
    when(homeState){
        is Result.HomeSuccess -> {
            news.value = homeState.items
        }
        is Result.Error -> {
            showErrorDialog.value = true
        }
        else -> {}
    }

    //Noticia principal
    val mainNews = news.value.filter { it.isMain }
    for (new in mainNews) {
        AddMainNew(new, navController)
    }

    // Lista de noticias secundarias
    val secondaryNews = news.value.filter { !it.isMain }
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(secondaryNews) { new ->
            AddSecondaryNew(new, navController)
        }
    }
}

@Composable
fun AddMainNew(new:News,  navController: NavHostController){
    val context = LocalContext.current
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val imageResId = context.resources.getIdentifier(new.imageRef1, "drawable", context.packageName)
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageResId!=0){
                Image(
                    painter = painterResource(imageResId),
                    contentDescription = "mainNews",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                        .clickable { navController.navigate("secondaryNews/${new.title}") }
                )
            }
            else {
                // Mockup en caso de no encontrar la imagen
                Image(
                    painter = painterResource(id = R.drawable.mockup),
                    contentDescription = "mainNews",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                        .clickable { navController.navigate("secondaryNews/${new.title}") }
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = new.title,
                color = Color.Red, fontSize = 15.sp, fontFamily = formula1Font
            )
            Text(
                text = new.description,
                color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun AddSecondaryNew(new:News, navController: NavHostController){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(new.imageRef1, "drawable", context.packageName)
    Row(
        modifier = Modifier.fillMaxWidth().background(Color(0xFFD3D3D3)).padding(10.dp).clickable { navController.navigate("secondaryNews/${new.title}")},
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(end = 15.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = new.title, color = Color.Red,
                fontSize = 14.sp, fontFamily = formula1Font,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = new.description, textAlign = TextAlign.Justify,
                color = Color.Black, fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        if (imageResId!=0){
            Image(
                painter = painterResource(imageResId),
                contentDescription = "secondaryNews/${new.title}",
                modifier = Modifier.size(80.dp).weight(0.5f)
            )
        }
        else {
            // Mockup en caso de no encontrar la imagen
            Image(
                painter = painterResource(id = R.drawable.mockup),
                contentDescription = "secondaryNews/${new.title}",
                modifier = Modifier.size(80.dp).weight(0.5f)
            )
        }
    }
}
