package com.example.sp_fitapp01.ui.ExercisesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.decode.*


@Composable
fun ExerciseDetailScreen(exerciseName: String, onBack: () -> Unit) {
    val context = LocalContext.current
    val exercise = defExercises().firstOrNull() { it.name == exerciseName }
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(exercise?.gifResourceId)
            .decoderFactory(GifDecoder.Factory())
            .size(Size.ORIGINAL)
            .build()
    )

    Scaffold(
        topBar = {
            ExerciseDetailHeader(onBack = onBack)
        }
    ) { innerPadding ->
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(rememberScrollState()) // Enables scrolling
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Exercise GIF
            Image(
                painter = painter,
                contentDescription = exerciseName,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // Keeps the aspect ratio of the image
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Exercise Description
            Text(
                text = exerciseName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (exercise != null) {
                Text(
                    text = exercise.description,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun ExerciseDetailHeader(onBack: () -> Unit) {
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
}
