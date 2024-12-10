package com.example.jedapaappf1.screens.Calendar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jedapaappf1.navigation.MyHeader
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel
import com.example.jedapaappf1.data.Circuit
import com.example.jedapaappf1.data.Result

@Composable
fun CalendarScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val viewModel: CalendarViewModel = viewModel()
    val calendarState by viewModel.calendarState.observeAsState()

    LaunchedEffect(Unit){
        viewModel.getCircuits()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.calendar_background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize().padding(top = 90.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ///////////////////////////// HEADER /////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Calendar",
                showBackArrow = true, userViewModel = userViewModel)
            /////////////////////////////////////////////////////////////////////

            //BODY
            CalendarBody(modifier = Modifier, calendarState = calendarState)

        }
    }
}

@Composable
fun CalendarBody(modifier: Modifier, calendarState:Result?){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val circuits = remember { mutableStateOf<List<Circuit>>(listOf()) }
    val showErrorDialog = remember { mutableStateOf(false) }

    when(calendarState){
        is Result.CalendarSuccess -> {
            circuits.value = calendarState.items
        }
        is Result.Error -> {
            showErrorDialog.value = true
        }
        else -> {}
    }


    Text(
        text = "Calendar", color = Color.Black,
        fontSize = 40.sp, fontFamily = formula1Font,
        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) { items(circuits.value) { circuit ->
        AddCircuit(circuit)
    }
    }

}

@Composable
fun AddCircuit(circuit: Circuit) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    var isFlipped by remember { mutableStateOf(false) }

    // Animación de rotación
    val rotation by animateFloatAsState(targetValue = if (isFlipped) 180f else 0f)
    val frontAlpha = if (rotation <= 90f) 1f else 0f
    val backAlpha = if (rotation > 90f) 1f else 0f
    val textRotation = if (isFlipped) 180f else 0f

    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(circuit.name+"_circuit", "drawable", context.packageName)

    Box(
        modifier = Modifier.padding(8.dp).width(300.dp).height(200.dp)
            .clickable { isFlipped = !isFlipped }
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        // Lado frontal
        if (frontAlpha > 0f) {
            if (imageResId != 0) {
                Image(
                    painter = painterResource(imageResId),
                    contentDescription = "Circuit of " + circuit.location,
                    modifier = Modifier.fillMaxSize().alpha(frontAlpha),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Mockup en caso de no encontrar la imagen
                Image(
                    painter = painterResource(id = R.drawable.mockup),
                    contentDescription = "item no encontrado",
                    modifier = Modifier
                )
            }
        }
        // Lado posterior
        if (backAlpha > 0f) {
            Column(
                modifier = Modifier
                    .fillMaxSize().graphicsLayer { rotationY = textRotation }
                    .alpha(backAlpha).padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título del circuito
                Text(
                    text = "Circuit of "+circuit.location,
                    fontSize = 18.sp, fontFamily = formula1Font,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                AddEvent("race", circuit.race)
                AddEvent("qualifying", circuit.qualifying)
                AddEvent("practice 3", circuit.practice3)
                AddEvent("practice 2", circuit.practice2)
                AddEvent("practice 1", circuit.practice1)
            }
        }
    }
}

@Composable
fun AddEvent(name: String, date: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$name: $date",
            fontSize = 16.sp, color = Color.Black, textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen(navController = NavHostController(LocalContext.current))
}
