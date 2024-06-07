package com.example.sp_fitapp01.ui.FinishScreen

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sp_fitapp01.ui.AppViewModelProvider
import com.example.sp_fitapp01.ui.HomeScreen.TopBarIcon
import kotlinx.coroutines.launch

/**
 * Composable function for displaying the finish screen of the Fitness Application.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param viewModel The view model for the finish screen.
 */
@Composable
fun FinishScreen(
    navController: NavHostController,
    viewModel: FinishScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    BackHandler {
        // Nevykonáva žiadnu akciu, čím efektívne blokuje tlačidlo "späť"
    }
    Scaffold(
        topBar = { TopBarIcon() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            FinishBody(navController = navController, viewModel = viewModel)
        }
    }
}

/**
 * Composable function for displaying the body section of the finish screen.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param viewModel The view model for the finish screen.
 */
@Composable
fun FinishBody(navController: NavHostController, viewModel: FinishScreenViewModel) {
    val feelings = (1..5).toList()
    val descriptions = listOf("Exhausted", "Tired", "Satisfied", "Energized", "Excellent")
    val coroutineScope = rememberCoroutineScope()
    val selectedFeel by viewModel.selectedFeeling.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            .background(if (selectedFeel.id == feeling) Color.Gray else Color.LightGray)
                            .clickable {
                                viewModel.setSelectedFeel(feeling, descriptions[index])
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
                viewModel.saveFeeling()
                navController.navigate("main_screen")
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(text = "Finish", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}


/**
 * Preview function for displaying the finish screen.
 */
@Preview
@Composable
fun FinishScreenPreview() {
    FinishScreen(navController = rememberNavController())
}