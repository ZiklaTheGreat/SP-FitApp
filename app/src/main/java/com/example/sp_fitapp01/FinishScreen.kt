package com.example.sp_fitapp01

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun FinishScreen(navController: NavHostController) {
    var selectedFeel by remember { mutableStateOf(1) }
    val feelings = (1..5).toList()
    val descriptions = listOf("Exhausted", "Tired", "Satisfied", "Energized", "Excellent")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // Pevná výška boxu
                    .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
                    .background(Color(0xFF8EBDEF)),
                contentAlignment = Alignment.Center // Toto zarovná obsah do stredu
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_transparent),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .scale(3f), // Zväčšenie obrázku
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Done!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Great Job",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "How do you feel?",
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                feelings.forEachIndexed { index, feeling ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = descriptions[index],
                            fontSize = 10.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(if (selectedFeel == feeling) Color.Gray else Color.LightGray)
                                .selectable(
                                    selected = (selectedFeel == feeling),
                                    onClick = { selectedFeel = feeling }
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = feeling.toString())
                        }
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate("main_screen") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Finish", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun FinishScreenPreview() {
    FinishScreen(navController = rememberNavController())
}