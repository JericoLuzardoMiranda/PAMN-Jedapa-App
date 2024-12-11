// Archivo: TypeNewsScreen.kt
package com.example.jedapaappf1.screens.TypeNews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.example.jedapaappf1.navigation.MyHeader
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel
import com.example.jedapaappf1.data.News
import com.example.jedapaappf1.data.Result

@Composable
fun TypeNewsScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel(), type:String) {
    val viewModel: TypeNewsViewModel = viewModel()
    val interviewsState by viewModel.interviewsState.observeAsState()
    val gamesState by viewModel.gamesState.observeAsState()
    val raceSummariesState by viewModel.raceSummariesState.observeAsState()
    val teamsDriversState by viewModel.teamsDriversState.observeAsState()
    LaunchedEffect(Unit){
        viewModel.getInterviews()
        viewModel.getGames()
        viewModel.getRaceSummaries()
        viewModel.getTeamsDrivers()
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
            MyHeader(
                navController = navController, currentScreen = "News",
                showBackArrow = true, userViewModel = userViewModel
            )
            ////////////////////////////////////////////////////////////////////

            when(type){
                "interviews" -> TypeNewsBody(interviewsState, navController)
                "games" -> TypeNewsBody(gamesState, navController)
                "raceSummaries" -> TypeNewsBody(raceSummariesState, navController)
                "teamsDrivers" -> TypeNewsBody(teamsDriversState, navController)
                else -> TypeNewsBody(interviewsState, navController)
            }

        }
    }
}

@Composable
fun TypeNewsBody(newsState: Result?, navController: NavHostController){
    val news = remember { mutableStateOf<List<News>>(listOf()) }
    val showErrorDialog = remember { mutableStateOf(false) }
    when(newsState){
        is Result.HomeSuccess -> {
            news.value = newsState.items
        }
        is Result.Error -> {
            showErrorDialog.value = true
        }
        else -> {}
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (new in news.value){
            NewsItem(new, navController)
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun NewsItem(news: News,  navController: NavHostController) {
    //val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(news.imageRef1, "drawable", context.packageName)
    Column(
        modifier = Modifier.fillMaxWidth().clickable { navController.navigate("secondaryNews/${news.title}") },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de la noticia
        if (imageResId!=0){
            Image(
                painter = painterResource(imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
        }
        else{
            Image(
                painter = painterResource(R.drawable.mockup),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
        }


        // Cuadro de texto debajo de la imagen
        Box(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0F969C))
                .padding(8.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = news.title, color = Color.Black, fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.formula1_bold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun TypeNewsScreenPreview() {
    TypeNewsScreen(navController = NavHostController(LocalContext.current), userViewModel = viewModel(), "interviews")
}

