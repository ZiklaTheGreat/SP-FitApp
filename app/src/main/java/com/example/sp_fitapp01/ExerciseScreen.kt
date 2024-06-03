package com.example.sp_fitapp01

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.decode.*

var defExercises = listOf(
Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise..."),
Exercise(R.drawable.sit_up, R.raw.sit_up, "Sit-up", "A sit-up is an abdominal endurance training..."),
Exercise(R.drawable.dip, R.raw.dip, "Dip", "A dip is an upper-body strength exercise..."),
Exercise(R.drawable.jumping_jack, R.raw.jumping_jack, "Jumping jack", "A jumping jack, also known as a star jump..."),
Exercise(R.drawable.stretch, R.raw.stretch, "Stretch", "Stretching is a form of physical exercise...")
)

@Composable
fun ExerciseListScreen(navController: NavHostController, exercises: List<Exercise> = defExercises) {
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

    if (selectedExercise == null) {
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
                            text = "Exercises",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            // Exercise List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(exercises) { exercise ->
                    ExerciseItem(exercise = exercise, onClick = { selectedExercise = it })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    } else {
        ExerciseDetailScreen(exercise = selectedExercise!!, onBack = { selectedExercise = null })
    }
}

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


@Composable
fun ExerciseDetailScreen(exercise: Exercise, onBack: () -> Unit) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(exercise.gifResourceId)
            .decoderFactory(GifDecoder.Factory())
            .size(Size.ORIGINAL)
            .build()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Exercise Detail",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exercise GIF
        Image(
            painter = painter,
            contentDescription = exercise.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Keeps the aspect ratio of the image
                .clip(RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp),
            contentScale = ContentScale.Fit
        )

        //Spacer(modifier = Modifier.height(16.dp))

        // Exercise Description
        Text(
            text = exercise.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = exercise.description,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun ExerciseScreenPreview() {
    //ExerciseListScreen(navController = rememberNavController())
    ExerciseDetailScreen(exercise = Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise...")) {
        
    }
}