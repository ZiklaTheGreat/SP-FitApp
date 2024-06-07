package com.example.sp_fitapp01.ui.PlansScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.data.dummyPlans
import com.example.sp_fitapp01.ui.ExercisesScreen.ExerciseItem
import com.example.sp_fitapp01.ui.HomeScreen.TopBarName

/**
 * Composable function for displaying the details of a workout plan.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param planName The name of the workout plan.
 */
@Composable
fun PlanDetailScreen(navController: NavHostController, planName: String) {

    val plan = dummyPlans().firstOrNull { it.name == planName }

    Scaffold(
        topBar = {
            TopBarName(navController = navController, name = stringResource(id = R.string.plan_detail))
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            item {
                PlanName(planName = planName)
            }

            if (plan != null) {
                items(plan.exercises) { exercise ->
                    ExerciseItem(exercise = exercise, onClick = { navController.navigate("exercise_detail_screen/${exercise.name}") })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {
                WorkoutButton(navController = navController, planName = planName)
            }
        }
    }
}

/**
 * Composable function for displaying the name of a workout plan.
 *
 * @param planName The name of the workout plan.
 */
@Composable
fun PlanName(planName: String) {
    Text(
        text = planName,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(14.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
}

/**
 * Composable function for displaying the "Start Workout" button.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param planName The name of the workout plan.
 */
@Composable
fun WorkoutButton(navController: NavHostController, planName: String) {
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
            Text(text = stringResource(id = R.string.start_workout))
        }
    }
}