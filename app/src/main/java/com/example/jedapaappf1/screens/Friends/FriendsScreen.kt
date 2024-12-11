package com.example.jedapaappf1.screens.Friends

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jedapaappf1.UserViewModel
import com.example.jedapaappf1.navigation.MyHeader
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import com.google.firebase.firestore.FirebaseFirestore

data class Friend(
    val name: String = "",
    val email: String = "",
)

@Composable
fun FriendsScreen(navController: NavHostController, userViewModel: UserViewModel) {
    var friendsList by remember { mutableStateOf<List<Friend>>(emptyList()) }

    val radialBrush = Brush.radialGradient(
        colors = listOf(Color(0xFF294D61), Color(0xFF6DA5C0)),
        radius = 1000f
    )

    // Se obtiene amigos desde Firebase
    LaunchedEffect(Unit) {
        getFriendsFromFirestore { friends ->
            friendsList = friends
        }
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
                navController = navController, currentScreen = "Friends",
                showBackArrow = true, userViewModel = userViewModel
            )
            ////////////////////////////////////////////////////////////////////

            Column(
                modifier = Modifier.fillMaxSize().background(radialBrush),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Se muestra la lista de amigos
                if (friendsList.isEmpty()) {
                    Text(
                        text = "No friends found",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 18.sp, color = Color.Gray
                    )
                } else {
                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        items(friendsList) { friend -> FriendListItem(friend) }
                    }
                }
            }

        }
    }
}

@Composable
fun FriendListItem(friend: Friend) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = friend.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF075E54)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = friend.email,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF757575)
                )
            }
        }
    }
}

// Función para obtener amigos desde Firestore
fun getFriendsFromFirestore(onComplete: (List<Friend>) -> Unit) {
    val firestore = FirebaseFirestore.getInstance()
    firestore.collection("friends")
        .get().addOnSuccessListener { querySnapshot ->
            val friendList = querySnapshot.documents.mapNotNull { document ->
                document.toObject(Friend::class.java)
            }
            onComplete(friendList)
        }
        .addOnSuccessListener { e ->
            println("Error getting friends list: $e")
        }
}