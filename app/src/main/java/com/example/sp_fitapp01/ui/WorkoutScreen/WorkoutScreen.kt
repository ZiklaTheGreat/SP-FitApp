package com.example.sp_fitapp01.ui.WorkoutScreen

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.ExercisesScreen.Exercise
import com.example.sp_fitapp01.ui.FinishScreen.FinishBody
import com.example.sp_fitapp01.ui.HomeScreen.TopBarIcon
import com.example.sp_fitapp01.ui.PlansScreen.dummyPlans

@Composable
fun WorkoutScreen(navController: NavHostController, planId: String, onWorkoutComplete: () -> Unit) {
    val plan = dummyPlans().firstOrNull { it.name == planId }

    if (plan == null) {
        navController.popBackStack()
        return
    }

    var currentExerciseIndex by remember { mutableStateOf(0) }
    var timeLeft by remember { mutableStateOf(30) }
    var isResting by remember { mutableStateOf(false) }

    val exercise = plan.exercises[currentExerciseIndex]
    val soundContext = LocalContext.current

    fun switchToNextPhase() {
        if (currentExerciseIndex == plan.exercises.size - 1) {
            onWorkoutComplete()
        } else {
            if (isResting) {
                currentExerciseIndex++
                isResting = false
                timeLeft = 30
            } else {
                isResting = true
                timeLeft = 10
            }
        }
    }

    DisposableEffect(currentExerciseIndex, isResting) {
        val timer = object : CountDownTimer(timeLeft * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt()
            }
            override fun onFinish() {
                timeLeft = 0
                switchToNextPhase()
            }
        }
        timer.start()
        onDispose {
            timer.cancel()
        }
    }

    LaunchedEffect(isResting) {
        playSound(soundContext, R.raw.notification)
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
            WorkoutBody(isResting = isResting, exercise = exercise, timeLeft = timeLeft, switchToNextPhase = {
                timeLeft = 0
                switchToNextPhase()
            })
        }
    }

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = if (isResting) "Rest" else exercise.name,
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        val context = LocalContext.current
//        val painter = rememberAsyncImagePainter(
//            model = if (!isResting) ImageRequest.Builder(context)
//                .data(exercise.gifResourceId)
//                .decoderFactory(GifDecoder.Factory())
//                .size(Size.ORIGINAL)
//                .build()
//            else ImageRequest.Builder(context)
//                .data(R.raw.rest1)
//                .decoderFactory(GifDecoder.Factory())
//                .size(Size.ORIGINAL)
//                .build()
//        )
//        Image(
//            painter = painter,
//            contentDescription = exercise.name,
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(1f)
//                .clip(RoundedCornerShape(8.dp))
//                .padding(horizontal = 16.dp),
//            contentScale = ContentScale.Fit
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60),
//            fontSize = 48.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = {
//                timeLeft = 0
//                switchToNextPhase()
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Skip")
//        }
//    }
}

@Composable
fun WorkoutBody(isResting: Boolean, exercise: Exercise, switchToNextPhase: () -> Unit, timeLeft: Int) {

    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = if (isResting) "Rest" else exercise.name,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(16.dp))
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = if (!isResting) ImageRequest.Builder(context)
            .data(exercise.gifResourceId)
            .decoderFactory(GifDecoder.Factory())
            .size(Size.ORIGINAL)
            .build()
        else ImageRequest.Builder(context)
            .data(R.raw.rest1)
            .decoderFactory(GifDecoder.Factory())
            .size(Size.ORIGINAL)
            .build()
    )
    Image(
        painter = painter,
        contentDescription = exercise.name,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        contentScale = ContentScale.Fit
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60),
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = {
            switchToNextPhase()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Skip")
    }
}

private fun playSound(context: Context, soundResourceId: Int) {
    val mediaPlayer = MediaPlayer.create(context, soundResourceId)
    mediaPlayer.start()
}

@Preview
@Composable
fun WorkoutScreenPreview() {
    WorkoutScreen(navController = rememberNavController(), "Horné telo", onWorkoutComplete = {})
}