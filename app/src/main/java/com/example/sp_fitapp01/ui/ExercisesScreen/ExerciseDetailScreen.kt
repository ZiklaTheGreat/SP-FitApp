package com.example.sp_fitapp01.ui.ExercisesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.sp_fitapp01.ui.HomeScreen.TopBarName

/**
 * Composable function for displaying detailed information about a specific exercise.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param exerciseName The name of the exercise to display details for.
 */
@Composable
fun ExerciseDetailScreen(navController: NavHostController, exerciseName: String) {
    val context = LocalContext.current
    val exercise = defExercises().firstOrNull { it.name == exerciseName }
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(exercise?.gifResourceId)
            .decoderFactory(GifDecoder.Factory())
            .size(Size.ORIGINAL)
            .build()
    )

    Scaffold(
        topBar = {
            TopBarName(navController = navController, name = exerciseName)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            if (exercise != null) {
                ExerciseBody(painter = painter, exerciseName = exerciseName, exercise = exercise)
            }
        }
    }
}

/**
 * Composable function for displaying the body section of the exercise detail screen.
 *
 * @param painter Painter for loading exercise GIF.
 * @param exerciseName The name of the exercise.
 * @param exercise The exercise object containing details.
 */
@Composable
fun ExerciseBody(painter: Painter, exerciseName: String, exercise: Exercise) {
    Image(
        painter = painter,
        contentDescription = exerciseName,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        contentScale = ContentScale.Fit
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = exerciseName,
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
