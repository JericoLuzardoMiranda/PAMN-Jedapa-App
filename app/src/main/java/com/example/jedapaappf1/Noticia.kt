// Archivo: Noticia.kt
package com.example.jedapaappf1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment

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
