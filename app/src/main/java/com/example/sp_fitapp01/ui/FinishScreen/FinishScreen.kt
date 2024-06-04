package com.example.sp_fitapp01.ui.FinishScreen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishScreen(navController: NavHostController,
                 viewModel: FinishScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
                ) {
    //val myViewModel: FinishScreenViewModel = viewModel()
    //val selectedFeel by myViewModel.selectedFeeling.collectAsState()
    val feelings = (1..5).toList()
    val descriptions = listOf("Exhausted", "Tired", "Satisfied", "Energized", "Excellent")
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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
                    .height(100.dp)
                    .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
                    .background(Color(0xFF8EBDEF)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_transparent),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .scale(3f),
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
                               // .background(if (viewModel.getSelectedFeel() == feeling) Color.Gray else Color.LightGray)
                                .clickable {
                                //    viewModel.setSelectedFeel(feeling, descriptions[index])
                                }
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
            onClick = {
                coroutineScope.launch {
                    //saveToDB(applicationContext = context, feel = selectedFeel)
                }
                navController.navigate("main_screen")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Finish", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

//private suspend fun saveToDB(applicationContext: Context, feel: Int) {
//    val db = Room.databaseBuilder(
//        applicationContext,
//        FeelingRoomDatabase::class.java, "database-name"
//    ).build()
//    val feelingDao = db.dao()
//    feelingDao.insert(Feeling(id = 0, name = "yo", value = feel))
//}

@Preview
@Composable
fun FinishScreenPreview() {
    FinishScreen(navController = rememberNavController())
}