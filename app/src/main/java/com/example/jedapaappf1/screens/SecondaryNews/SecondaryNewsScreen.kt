// Archivo: SecondaryNewsScreen.kt
package com.example.jedapaappf1.screens.SecondaryNews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.jedapaappf1.screens.Home.HomeViewModel
import com.example.jedapaappf1.screens.TypeNews.TypeNewsViewModel

@Composable
fun SecondaryNewsScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel(), id:String?) {
    val viewModel: HomeViewModel = viewModel()
    val viewModel2: TypeNewsViewModel = viewModel()
    val homeState by viewModel.homeState.observeAsState()
    val interviewsState by viewModel2.interviewsState.observeAsState()
    val gamesState by viewModel2.gamesState.observeAsState()
    val raceSummariesState by viewModel2.raceSummariesState.observeAsState()
    val teamsDriversState by viewModel2.teamsDriversState.observeAsState()
    LaunchedEffect(Unit){
        viewModel.getNews()
        viewModel2.getInterviews()
        viewModel2.getGames()
        viewModel2.getRaceSummaries()
        viewModel2.getTeamsDrivers()
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
                navController = navController, currentScreen = "",
                showBackArrow = true, userViewModel = userViewModel
            )
            ////////////////////////////////////////////////////////////////////

            SecondaryNewsBody(homeState, interviewsState, gamesState, raceSummariesState, teamsDriversState,  navController, id)

        }
    }
}

@Composable
fun SecondaryNewsBody(homeState: Result?, interviewsState: Result?, gamesState: Result?, raceSummariesState: Result?, teamsDriversState: Result?, navController: NavHostController, id:String?){
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

    var theNews =  news.value.firstOrNull { it.title==id }

    if(theNews==null){
        when(interviewsState){
            is Result.HomeSuccess -> {
                news.value = interviewsState.items
            }
            is Result.Error -> {
                showErrorDialog.value = true
            }
            else -> {}
        }
        theNews =  news.value.firstOrNull { it.title==id }
    }

    if(theNews==null){
        when(gamesState){
            is Result.HomeSuccess -> {
                news.value = gamesState.items
            }
            is Result.Error -> {
                showErrorDialog.value = true
            }
            else -> {}
        }
        theNews =  news.value.firstOrNull { it.title==id }
    }

    if(theNews==null){
        when(raceSummariesState){
            is Result.HomeSuccess -> {
                news.value = raceSummariesState.items
            }
            is Result.Error -> {
                showErrorDialog.value = true
            }
            else -> {}
        }
        theNews =  news.value.firstOrNull { it.title==id }
    }

    if(theNews==null){
        when(teamsDriversState){
            is Result.HomeSuccess -> {
                news.value = teamsDriversState.items
            }
            is Result.Error -> {
                showErrorDialog.value = true
            }
            else -> {}
        }
        theNews =  news.value.firstOrNull { it.title==id }
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (theNews!=null) {
            DisplayNew(theNews)
            //MostReadNews()
        }
        else{
            Text("Error 404: News not found")
        }
    }

}

@Composable
fun DisplayNew(news:News){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(news.imageRef1, "drawable", context.packageName)
    val imageResId2 = context.resources.getIdentifier(news.imageRef2, "drawable", context.packageName)
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageResId!=0){
                Image(
                    painter = painterResource(imageResId), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                )
            }
            else{
                Image(
                    painter = painterResource(R.drawable.mockup), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = news.title,
                color = Color.Red, fontSize = 25.sp, fontFamily = formula1Font,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = news.newsTitle2,
                color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = news.paragraph1,
                color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = news.paragraph2,
                color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 18.dp)
            )
            if (imageResId2!=0){
                Image(
                    painter = painterResource(imageResId2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(300.dp).height(150.dp).padding(bottom = 18.dp)
                )
            }
            else{
                Image(
                    painter = painterResource(R.drawable.mockup),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(300.dp).height(150.dp).padding(bottom = 18.dp)
                )
            }
            Text(
                text = news.paragraph3,
                color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = news.paragraph4,
                color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = news.paragraph5,
                color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun MostReadNews(){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val image = painterResource(R.drawable.mockup)
    Text(
        text = "Most Read News", color = Color.Red,
        fontSize = 25.sp, fontFamily = formula1Font,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(1f).padding(4.dp).height(100.dp)
        )

        Image(
            painter = image, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(1f).padding(4.dp).height(100.dp)
        )

        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(1f).padding(4.dp).height(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SecondaryNewsScreenPreview() {
    SecondaryNewsScreen(navController = NavHostController(LocalContext.current), userViewModel = viewModel(), "hamilton")
}

