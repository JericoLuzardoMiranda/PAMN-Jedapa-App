package com.example.jedapaappf1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jedapaappf1.ui.theme.JedapaAppF1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JedapaAppF1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    JedapaF1AppImage(
                        name = "Nombre",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}


// Clase de datos para representar una noticia
data class Noticia(
    val titulo: String,
    val descripcion: String,
    val imagenRes: Int
)

@Composable
fun NoticiasList(noticias: List<Noticia>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(noticias) { noticia ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD3D3D3))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = noticia.titulo,
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = noticia.descripcion,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }

                Image(
                    painter = painterResource(noticia.imagenRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .weight(0.5f)
                )
            }
        }
    }
}

@Composable
fun JedapaF1AppImage(name: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.mockup)
    val image_logo = painterResource(R.drawable.logo)
    val image_logo_F1 = painterResource(R.drawable.logo_f1)
    val formula1Font = FontFamily(Font(R.font.formula1_bold))

    val noticias = listOf(
        Noticia(
            titulo = "Gran Premio de Abu Dhabi",
            descripcion = "El circuito de Yas Marina espera una carrera épica.",
            imagenRes = R.drawable.mockup
        ),
        Noticia(
            titulo = "Hamilton domina la clasificación",
            descripcion = "Lewis Hamilton toma la pole en un sábado emocionante.",
            imagenRes = R.drawable.mockup
        ),
        Noticia(
            titulo = "Red Bull y sus estrategias",
            descripcion = "Verstappen buscará cerrar la temporada con estilo.",
            imagenRes = R.drawable.mockup
        )
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Primera fila
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = image_logo,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(120.dp)
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.width(60.dp))

                Button(
                    onClick = { },
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
                    onClick = { },
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

            // Segunda fila
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF294D61))
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = image_logo_F1,
                    contentDescription = null,
                    modifier = Modifier.width(55.dp)
                )

                Text(
                    text = "Menu",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = formula1Font
                )
            }

            // Tercera columna principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Primera sub-columna
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }

                // Segunda sub-columna
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bienvenido al mundo de la Fórmula 1",
                        color = Color.Red,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Disfruta las últimas noticias y eventos",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Tercera sub-columna: Lista de noticias
                NoticiasList(
                    noticias = noticias,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "My preview")
@Composable
fun JedapaF1AppPreview() {
    JedapaAppF1Theme {
        JedapaF1AppImage("Nombre")
    }
}


