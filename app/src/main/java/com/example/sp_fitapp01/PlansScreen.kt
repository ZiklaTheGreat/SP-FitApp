package com.example.sp_fitapp01

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun PlansScreen(navController: NavHostController) {
    val plans = remember { dummyPlans() }
    var selectedPlan by remember { mutableStateOf<Plan?>(null) }
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

    if (selectedExercise != null) {
        ExerciseDetailScreen(exercise = selectedExercise!!, onBack = { selectedExercise = null })
    } else if (selectedPlan == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
                    .background(Color(0xFF8EBDEF))
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.popBackStack() }
                    )
                    Spacer(modifier = Modifier.weight(0.8f))
                    Box(modifier = Modifier) {
                        Text(
                            text = "Plans",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            // Plan List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(plans) { plan ->
                    PlanItem(plan = plan, onClick = { selectedPlan = it })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    } else {
        PlanDetailScreen(
            plan = selectedPlan!!,
            onBack = { selectedPlan = null },
            onExerciseClick = { selectedExercise = it },
            navController = navController
        )
    }
}

@Composable
fun PlanItem(plan: Plan, onClick: (Plan) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(plan) }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = plan.imageResId),
            contentDescription = plan.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = plan.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun PlanDetailScreen(navController: NavHostController, plan: Plan, onBack: () -> Unit, onExerciseClick: (Exercise) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(modifier = Modifier
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
        Spacer(modifier = Modifier.height(16.dp))

        // Plan Name
        Text(
            text = plan.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(14.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Exercise Sequence
        for (exercise in plan.exercises) {
            ExerciseItem(exercise = exercise, onClick = { onExerciseClick(exercise) })
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Button Start Workout
        Button(
            onClick = {
                val planId = plan.name
                navController.navigate("workout_screen/$planId")
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Start Workout")
        }
    }
}




// Dummy data for plans
data class Plan(val name: String, val imageResId: Int, val exercises: List<Exercise>)

fun dummyPlans(): List<Plan> {
    return listOf(
        Plan("Horné telo", R.drawable.push_up, listOf(
            Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
            Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise...")
        )),
        Plan("Dolné telo", R.drawable.push_up, listOf(
            Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
            Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise...")
        )),
        // Add more plans as needed
    )
}

@Preview(showBackground = true)
@Composable
fun PlansScreenPreview() {
    //PlansScreen(navController = rememberNavController())
    PlanDetailScreen(plan = Plan("Horné telo", R.drawable.push_up, listOf(
        Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
        Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise...")
    )), onBack = {  },
        onExerciseClick = {  },
        navController = rememberNavController())
}