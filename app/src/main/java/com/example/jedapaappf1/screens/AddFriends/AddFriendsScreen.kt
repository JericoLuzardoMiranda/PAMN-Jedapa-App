package com.example.jedapaappf1.screens.AddFriends

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


@Composable
fun AddFriendsScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()){
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    var selectedTabIndex by remember { mutableStateOf(0) }

    var resultadoEscaneo by remember { mutableStateOf("") }
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = {  result ->
            resultadoEscaneo = result.contents?:"Sin resultado"
        }
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            /////////////////////////////HEADER/////////////////////////////////
            MyHeader(navController = navController, currentScreen = "Add friends",
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
                            text = "My Code",
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
                            text = "Scan Code",
                            color = if (selectedTabIndex == 1) Color.White else Color.LightGray,
                            fontSize = 18.sp, fontFamily = formula1Font
                        )
                    }
                )
            }

            when (selectedTabIndex) {
                0 -> MyCodeTabContent()
                1 -> ScanCodeTabContent()
            }
        }
    }

}

@Composable
fun MyCodeTabContent() {
    val formula1Font = FontFamily(Font(R.font.formula1_bold))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = "My user icon",
                modifier = Modifier.width(150.dp).height(150.dp)
                    .align(Alignment.TopCenter).offset(y = 0.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.figura),
                contentDescription = "My code image",
                modifier = Modifier.fillMaxHeight().width(350.dp).offset(y = 20.dp)
            )

            Text(
                text = "MY CODE",
                color = Color.White, fontFamily = formula1Font,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ScanCodeTabContent() {
    var scannedResult by remember { mutableStateOf("") }
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result -> scannedResult = result.contents ?: "No result" }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF6200EE), Color(0xFF3700B3))
                )
            )
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
                modifier = Modifier.padding(8.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(16.dp))
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

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEDEDED))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Scanned Result:", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = scannedResult.ifEmpty { "No result scanned" },
                        fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddFriendsScreenPreview() {
    AddFriendsScreen(navController = NavHostController(LocalContext.current))
}