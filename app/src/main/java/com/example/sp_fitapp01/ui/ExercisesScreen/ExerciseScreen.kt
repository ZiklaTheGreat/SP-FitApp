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
import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.HomeScreen.TopBarName

//var defExercises = listOf(
//Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
//Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise..."),
//Exercise(R.drawable.sit_up, R.raw.sit_up, "Sit-up", "A sit-up is an abdominal endurance training..."),
//Exercise(R.drawable.dip, R.raw.dip, "Dip", "A dip is an upper-body strength exercise..."),
//Exercise(R.drawable.jumping_jack, R.raw.jumping_jack, "Jumping jack", "A jumping jack, also known as a star jump..."),
//Exercise(R.drawable.stretch, R.raw.stretch, "Stretch", "Stretching is a form of physical exercise...")
//)



//object HomeDestination : NavigationDestination {
//    override val route = "exercises"
//    override val titleRes = R.string.app_name
//}

@Composable
fun ExerciseListScreen(navController: NavHostController, exercises: List<Exercise> = defExercises()) {
    Scaffold(
        topBar = {
            TopBarName(navController = navController, name = "Exercises")
        }
    ) { innerPadding ->
        // Exercise List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp) // Additional padding for inner content
        ) {
            items(exercises) { exercise ->
                ExerciseItem(exercise = exercise, onClick = { navController.navigate("exercise_detail_screen/${exercise.name}") } )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

//@Composable
//fun ExerciseListHeader(navController: NavHostController) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
//            .padding(24.dp)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowBack,
//                contentDescription = "Back",
//                modifier = Modifier
//                    .size(24.dp)
//                    .clickable { navController.popBackStack() }
//            )
//            Spacer(modifier = Modifier.weight(0.8f))
//            Box(modifier = Modifier) {
//                Text(
//                    text = "Exercises",
//                    fontSize = 30.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black,
//                    textDecoration = TextDecoration.Underline
//                )
//            }
//            Spacer(modifier = Modifier.weight(1f))
//        }
//    }
//}

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




fun defExercises(): List<Exercise> {
    return listOf(
        Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
        Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise..."),
        Exercise(R.drawable.sit_up, R.raw.sit_up, "Sit-up", "A sit-up is an abdominal endurance training..."),
        Exercise(R.drawable.dip, R.raw.dip, "Dip", "A dip is an upper-body strength exercise..."),
        Exercise(R.drawable.jumping_jack, R.raw.jumping_jack, "Jumping jack", "A jumping jack, also known as a star jump..."),
        Exercise(R.drawable.stretch, R.raw.stretch, "Stretch", "Stretching is a form of physical exercise...")
    )
}


//@Preview(showBackground = true)
//@Composable
//fun ExerciseScreenPreview() {
//    //ExerciseListScreen(navController = rememberNavController())
//    ExerciseDetailScreen(exercise = Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise...")) {
//
//    }
//}