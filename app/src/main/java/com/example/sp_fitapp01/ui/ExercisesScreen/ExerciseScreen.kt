package com.example.sp_fitapp01.ui.ExercisesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sp_fitapp01.ui.HomeScreen.TopBarName

/**
 * Composable function for displaying a list of exercises.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param exercises The list of exercises to display.
 */
@Composable
fun ExerciseListScreen(navController: NavHostController, exercises: List<Exercise> = defExercises()) {
    Scaffold(
        topBar = {
            TopBarName(navController = navController, name = "Exercises")
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(exercises) { exercise ->
                ExerciseItem(exercise = exercise, onClick = { navController.navigate("exercise_detail_screen/${exercise.name}") } )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

/**
 * Composable function for displaying an item in the exercise list.
 *
 * @param exercise The exercise to display.
 * @param onClick Callback function invoked when the item is clicked.
 */
@Composable
fun ExerciseItem(exercise: Exercise, onClick: (Exercise) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(exercise) }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = exercise.imageResId),
            contentDescription = exercise.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = exercise.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

