package com.example.jedapaappf1.screens.AddFriends

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jedapaappf1.navigation.MyHeader
import com.example.jedapaappf1.R
import com.example.jedapaappf1.UserViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AddFriendsScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val pagerState = rememberPagerState(initialPage = 0) // Controla las páginas
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Add friends",
                showBackArrow = true, userViewModel = userViewModel
            )
            ////////////////////////////////////////////////////////////////////

            // TabRow con sincronización al pagerState
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color(0xFF294D61),
                contentColor = Color.White,
                indicator = { tabPositions ->
                    Box(
                        Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .height(4.dp).background(Color(0xFFFE0809))
                    )
                }
            ) {

                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(0) }
                    },
                    text = {
                        Text(
                            text = "My Code",
                            color = if (pagerState.currentPage == 0) Color.White else Color.LightGray,
                            fontSize = 18.sp, fontFamily = formula1Font
                        )
                    }
                )

                Tab(
                    selected = pagerState.currentPage == 1,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(1) }
                    },
                    text = {
                        Text(
                            text = "Scan Code",
                            color = if (pagerState.currentPage == 1) Color.White else Color.LightGray,
                            fontSize = 18.sp, fontFamily = formula1Font
                        )
                    }
                )
            }

            // HorizontalPager para deslizar entre pantallas
            HorizontalPager(
                state = pagerState, count = 2,
                modifier = Modifier.weight(2f)
            ) { page ->
                when (page) {
                    0 -> MyCodeTabContent(userViewModel)
                    1 -> ScanCodeTabContent()
                }
            }
        }
    }
}


@Composable
fun MyCodeTabContent(userViewModel: UserViewModel = viewModel()) {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    val username = userViewModel.username ?: "User"
    val userId = userViewModel.userId ?: "no_id"
    val userEmail = userViewModel.email ?: "no_email"

    val qrCodeBitmap = generateQRCode("$username:$userId:$userEmail")

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFFEFEFEF)),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier.width(300.dp).height(400.dp).clip(RoundedCornerShape(25.dp))
                .background(Color(0xFF294D61)).border(2.dp, Color.White, RoundedCornerShape(25.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_icon),
                    contentDescription = "My user icon",
                    modifier = Modifier.size(150.dp).clip(CircleShape).border(4.dp, Color.White, CircleShape)
                )

                Text(
                    text = username.uppercase(), fontSize = 24.sp, fontFamily = formula1Font,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                // Código QR
                qrCodeBitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "QR Code",
                        modifier = Modifier.size(200.dp).padding(top = 20.dp)
                    )
                }
            }
        }

    }

}

// Función para generar el código QR
fun generateQRCode(content: String): Bitmap? {
    return try {
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix =qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.Black.toArgb() else Color.White.toArgb())
            }
        }
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


@Composable
fun ScanCodeTabContent() {
    var scannedResult by remember { mutableStateOf("") }
    var scannedName by remember { mutableStateOf("") }
    var scannedEmail by remember { mutableStateOf("") }
    var isFriendAdded by remember { mutableStateOf(false) }

    val radialBrush = Brush.radialGradient(
        colors = listOf(Color(0xFF294D61), Color(0xFF6DA5C0)),
        radius = 1000f
    )

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            scannedResult = result.contents ?: "No result"
            val parts = scannedResult.split(":")
            if (parts.size == 3) {
                scannedName = parts[0]
                scannedEmail = parts[2]
            }
        }
    )

    val firestore = FirebaseFirestore.getInstance()
    val snackbarHostState = remember { SnackbarHostState() } // Para mostrar un mensaje después de añadir amigo

    Box(
        modifier = Modifier.fillMaxSize().background(radialBrush)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    val options = ScanOptions().apply {
                        setOrientationLocked(true)
                        setPrompt("Scan a code")
                        setCameraId(0)
                    }
                    scanLauncher.launch(options)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03DAC5),
                    contentColor = Color.Black
                ),
                modifier = Modifier.padding(8.dp).shadow(8.dp, shape = RoundedCornerShape(16.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.qr_code_scanner),
                    contentDescription = "Custom camera icon",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Scan Code",
                    fontSize = 16.sp, fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (scannedResult.isNotEmpty() && !isFriendAdded) {
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
                                text = scannedName,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF075E54)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = scannedEmail,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF757575)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // Botón con ícono para añadir amigo
                        IconButton(
                            onClick = {
                                val friendData = hashMapOf(
                                    "name" to scannedName,
                                    "email" to scannedEmail
                                )
                                firestore.collection("friends")
                                    .add(friendData).addOnSuccessListener { documentReference ->
                                        println("Friend added with ID: ${documentReference.id}")
                                        isFriendAdded = true
                                    }
                                    .addOnFailureListener { e ->
                                        println("Error adding friend: $e")
                                    }
                            },
                            modifier = Modifier.clip(CircleShape).size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.add_person_icon),
                                contentDescription = "Add friend", tint = Color(0xFF075E54),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        }

        // Se muestra un mensaje al añadir un amigo
        if (isFriendAdded) {
            LaunchedEffect(isFriendAdded) {
                snackbarHostState.showSnackbar("Friend added successfully!")
            }
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddFriendsScreenPreview() {
    AddFriendsScreen(navController = NavHostController(LocalContext.current))
}