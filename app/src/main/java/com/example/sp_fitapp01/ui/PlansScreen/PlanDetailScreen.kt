package com.example.sp_fitapp01.ui.PlansScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sp_fitapp01.ui.ExercisesScreen.Exercise
import com.example.sp_fitapp01.ui.ExercisesScreen.ExerciseDetailScreen
import com.example.sp_fitapp01.ui.ExercisesScreen.ExerciseItem
import com.example.sp_fitapp01.ui.HomeScreen.TopBarName

@Composable
fun PlanDetailScreen(navController: NavHostController, planName: String) {
    //var selectedExercise by remember { mutableStateOf<Exercise?>(null) }
    val plan = dummyPlans().firstOrNull { it.name == planName }
    Scaffold(
        topBar = {
            TopBarName(navController = navController, name = "Plan Detail")
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(horizontal = 16.dp) // Add any additional padding you need
        ) {
            item {
                // Plan Name
                Text(
                    text = planName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(14.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (plan != null) {
                items(plan.exercises) { exercise ->
                    //ExerciseItem(exercise = exercise, onClick = { onExerciseClick(exercise) })
                    ExerciseItem(exercise = exercise, onClick = { navController.navigate("exercise_detail_screen/${exercise.name}") })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {
                // Center the button horizontally
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            navController.navigate("workout_screen/$planName")
                        }
                    ) {
                        Text(text = "Start Workout")
                    }
                }
            }
        }
    }
}

@Composable
fun PlanDetailHeader(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
            .background(Color(0xFF8EBDEF))
            .padding(24.dp)
    ) {
        // Header Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBack() }
            )
            Spacer(modifier = Modifier.weight(0.8f))
            Text(
                text = "Plan Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

