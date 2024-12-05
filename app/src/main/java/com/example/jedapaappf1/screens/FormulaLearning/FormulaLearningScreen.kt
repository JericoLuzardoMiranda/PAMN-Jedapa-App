package com.example.jedapaappf1.screens.FormulaLearning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

// Clase de datos para representar la pregunta
data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)

@Composable
fun FormulaLearningScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    var selectedTabIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Formula Learning",
                showBackArrow = true, userViewModel = userViewModel
            )
            ////////////////////////////////////////////////////////////////////

            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color(0xFF294D61),
                contentColor = Color.White,
                indicator = { tabPositions ->
                    Box(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .height(4.dp).background(Color(0xFFFE0809))
                    )
                }
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = {
                        Text(
                            text = "Information",
                            color = if (selectedTabIndex == 0) Color.White else Color.LightGray,
                            fontSize = 18.sp, fontFamily = formula1Font
                        )
                    }
                )

                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = {
                        Text(
                            text = "Quiz",
                            color = if (selectedTabIndex == 1) Color.White else Color.LightGray,
                            fontSize = 18.sp, fontFamily = formula1Font
                        )
                    }
                )
            }

            when (selectedTabIndex) {
                0 -> InformationTabContent()
                1 -> QuizTabContent()
            }
        }
    }

}


@Composable
fun InformationTabContent() {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "What is Formula Learning?", fontSize = 20.sp,
            fontFamily = formula1Font, textAlign = TextAlign.Center,
            color = Color.Black
        )

        Text(
            text = "Formula Learning is your gateway to unlocking the secrets and insights behind the exhilarating world of Formula 1 racing. It's an educational journey designed to immerse enthusiasts, newcomers, and aspiring racing aficionados alike into the thrilling universe of motorsport.\n" +
                    "Through Formula Learning, you'll delve into the rich history of Formula 1, exploring its evolution from its humble beginnings to the global spectacle it is today. Discover the intricate regulations and cutting-edge technology that propel these high-speed machines around the track, gaining a deeper understanding of the engineering marvels behind the sport.\n" +
                    "But Formula Learning is not just about facts and figures; it's about embracing the passion and strategy that define each race. From analyzing race circuits and mastering strategic maneuvers to decoding the language of the paddock, Formula Learning equips you with the knowledge and insights to appreciate every twist and turn of the F1 season.",
            fontSize = 14.sp, textAlign = TextAlign.Justify,
            color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 5.dp, bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            color = Color.Gray, thickness = 1.dp, modifier = Modifier.width(300.dp).padding(bottom = 15.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "History of Formula 1", fontSize = 20.sp,
            fontFamily = formula1Font, textAlign = TextAlign.Center, color = Color.Black
        )

        Text(
            text = "Formula 1, often abbreviated as F1, is the pinnacle of motorsport. It traces its roots back to the early 20th century...\n" +
                    "From its inception in 1950, Formula 1 has evolved into a global phenomenon, captivating audiences around the world...",
            fontSize = 14.sp, textAlign = TextAlign.Justify, color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 5.dp, bottom = 8.dp)
        )

        Text(
            text = "Key Milestones", fontSize = 16.sp,
            fontFamily = formula1Font, textAlign = TextAlign.Center, color = Color.Black
        )

        Text(
            text = "1950: Inaugural Formula 1 World Championship season held at Silverstone, United Kingdom.\n" +
                    "1970s: Introduction of groundbreaking technology, such as aerodynamics and turbocharging.\n" +
                    "1980s: Emergence of iconic drivers like Ayrton Senna and Alain Prost.\n" +
                    "2000s: Dominance of teams like Ferrari and drivers like Michael Schumacher.\n" +
                    "2010s: Introduction of hybrid power units and the rise of Lewis Hamilton as a dominant force.",
            fontSize = 14.sp, textAlign = TextAlign.Justify, color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 5.dp, bottom = 15.dp)
        )

        Text(
            text = "Evolution of Regulations", fontSize = 16.sp,
            fontFamily = formula1Font, textAlign = TextAlign.Center, color = Color.Black
        )

        Text(
            text = "Throughout its history, Formula 1 has seen numerous changes in regulations...\n" +
                    "These regulations govern various aspects of the sport, including car design, engine specifications, safety standards, and more...",
            fontSize = 14.sp, textAlign = TextAlign.Justify, color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 5.dp, bottom = 15.dp)
        )

        Text(
            text = "Historic Races", fontSize = 16.sp,
            fontFamily = formula1Font, textAlign = TextAlign.Center, color = Color.Black
        )

        Text(
            text = "Formula 1 has witnessed countless memorable races that have left an indelible mark on the sport...\n" +
                    "From the Monaco Grand Prix to the Italian Grand Prix at Monza, each race has its own unique history and challenges...",
            fontSize = 14.sp, textAlign = TextAlign.Justify, color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 5.dp, bottom = 15.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            color = Color.Gray, thickness = 1.dp, modifier = Modifier.width(300.dp).padding(bottom = 15.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Regulations and Technology", fontSize = 20.sp,
            fontFamily = formula1Font, textAlign = TextAlign.Center, color = Color.Black
        )

        Text(
            text = "Formula 1 is governed by a comprehensive set of regulations that dictate everything from car design to race procedures...\n" +
                    "Throughout the years, Formula 1 has been at the forefront of technological innovation, pushing the boundaries of what's possible in motorsport...\n" +
                    "Formula 1, as one of the most prestigious racing competitions globally, operates under a stringent framework of regulations meticulously crafted to govern every aspect of the sport. These regulations serve as the bedrock, dictating not only the technical specifications of the cars but also the conduct of races, ensuring fairness, safety, and competitiveness.\n" +
                    "With each passing season, the regulations undergo refinement and adaptation, reflecting the dynamic nature of Formula 1. The sport's governing body, the Fédération Internationale de l'Automobile (FIA), collaborates with teams, engineers, and stakeholders to strike a delicate balance between innovation and standardization. This delicate dance between tradition and progress has led to the evolution of Formula 1 into the pinnacle of motorsport.\n" +
                    "Technological innovation is at the heart of Formula 1's DNA. Teams invest millions in research and development to gain a competitive edge, exploring cutting-edge technologies and pushing the boundaries of engineering excellence. Whether it's advancements in aerodynamics, materials science, or powertrain technology, Formula 1 serves as a crucible for innovation, driving progress not only within the sport but also in broader automotive and technological industries.\n" +
                    "One of the most notable technological advancements in recent years has been the introduction of hybrid power units. These sophisticated powertrains, comprising a turbocharged internal combustion engine and energy recovery systems, have revolutionized the sport, enhancing performance while promoting sustainability and efficiency.\n" +
                    "In addition to technical regulations, Formula 1 also embraces technological innovation in the realm of data analytics and simulation. Teams harness the power of big data and computational modeling to optimize performance, analyze race strategies, and enhance driver training, providing a competitive advantage in the fiercely contested world of Formula 1.\n" +
                    "Ultimately, Formula 1 stands as a testament to human ingenuity and the relentless pursuit of excellence. It's a showcase of technological prowess, where innovation converges with tradition, and where the pursuit of speed intertwines with the quest for sustainability. As Formula 1 continues to push the boundaries of what's possible, it remains an unparalleled platform for showcasing the very best of engineering, athleticism, and teamwork.",
            fontSize = 14.sp, textAlign = TextAlign.Justify, color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 5.dp, bottom = 8.dp)
        )
    }

}


@Composable
fun QuizTabContent() {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))

    // Preguntas y respuestas
    val questions = listOf(
        Question(
            text = "What year marked the inaugural Formula 1 World Championship season?",
            options = listOf("1948", "1950", "1962"),
            correctAnswer = "1950"
        ),
        Question(
            text = "Which legendary driver emerged in the 1980s?",
            options = listOf("Michael Schumacher","Niki Lauda","Ayrton Senna"),
            correctAnswer = "Ayrton Senna"
        ),
        Question(
            text = "What was a significant technological advancement introduced in the 1970s?",
            options = listOf("Carbon fiber chassis","Hybrid power units","Turbocharging"),
            correctAnswer = "Turbocharging"
        ),
        Question(
            text = "What is the current maximum number of engines allowd per driver for the entire season?",
            options = listOf("2","3","4"),
            correctAnswer = "4"
        ),
        Question(
            text = "Which aerodynamic feature aims to reduce drag on straights?",
            options = listOf("Front wing","Rear wing","Diffuser"),
            correctAnswer = "Rear wing"
        ),
        Question(
            text = "What does DRS stand for?",
            options = listOf("Drag Reduction System","Downforce Reduction System","Driving Reduction System"),
            correctAnswer = "Drag Reduction System"
        )
    )

    // Estado de la pregunta actual
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val currentQuestion = questions[currentQuestionIndex]

    // Estado para la respuesta depende de que si acierta o falla
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isAnswerCorrect by remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Se muestra la pregunta
        Text(
            text = currentQuestion.text, textAlign = TextAlign.Justify,
            fontSize = 20.sp, fontFamily = formula1Font,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Se muestra múltiples respuestas
        currentQuestion.options.forEach { option ->
            Button(
                onClick = {
                    selectedAnswer = option
                    isAnswerCorrect = (option == currentQuestion.correctAnswer)
                },
                colors = if (selectedAnswer == option) {
                    if (isAnswerCorrect == true) { ButtonDefaults.buttonColors(containerColor = Color.Green) }
                    else { ButtonDefaults.buttonColors(containerColor = Color.Red) }
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) { Text(text = option, color = Color.Black,
                fontSize = 18.sp, fontFamily = formula1Font) }
        }

        // Botones "Anterior" y "Siguiente"
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentQuestionIndex > 0) {
                        currentQuestionIndex--
                        selectedAnswer = null
                        isAnswerCorrect = null
                    }
                },
                // Se desactiva el botón si estamos en la primera pregunta
                enabled = currentQuestionIndex > 0
            ) { Text(text = "Previous") }

            Button(
                onClick = {
                    if (currentQuestionIndex < questions.lastIndex) {
                        currentQuestionIndex++
                        selectedAnswer = null
                        isAnswerCorrect = null
                    }
                },
                // Se desactiva el botón si estamos en la última pregunta
                enabled = currentQuestionIndex < questions.lastIndex
            ) { Text(text = "Next") }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FormulaLearningScreenPreview() {
    FormulaLearningScreen(navController = NavHostController(LocalContext.current))
}