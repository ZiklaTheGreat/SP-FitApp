package com.example.sp_fitapp01.ui.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sp_fitapp01.ui.PlansScreen.PlansScreen
import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.ExercisesScreen.ExerciseListScreen
import com.example.sp_fitapp01.ui.FinishScreen.FinishScreen
import com.example.sp_fitapp01.ui.MainApp
import com.example.sp_fitapp01.ui.WorkoutScreen.WorkoutScreen
import com.example.sp_fitapp01.ui.StatScreen.StatScreen
import com.example.sp_fitapp01.ui.navigation.NavigationDestination

//object HomeDestination : NavigationDestination {
//    override val route = "home"
//    override val titleRes = R.string.app_name
//}


//@Composable
//fun MainApp() {
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = "main_screen") {
//        composable("main_screen") { MainScreen(navController) }
//        composable("exercise_screen") { ExerciseListScreen(navController) }
//        composable("plan_screen") { PlansScreen(navController) }
//        composable(
//            "workout_screen/{planId}",
//            arguments = listOf(navArgument("planId") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val planId = backStackEntry.arguments?.getString("planId") ?: ""
//            WorkoutScreen(navController = navController, planId = planId,
//                onWorkoutComplete = { navController.navigate("finish_screen") })
//        }
//        composable("finish_screen") { FinishScreen(navController) }
//        composable("stat_screen") { StatScreen(navController) }
//    }
//}

@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .background(Color(0xFF8EBDEF))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "FitApp",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "your fitness journey, today",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "MENU",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        // Logo
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )

        // Workout Button
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("plan_screen") },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Text(
                text = "Workout",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Footer Buttons
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            FooterButton(text = "Exercises") { navController.navigate("exercise_screen") }
            //FooterButton(text = "Plans") { navController.navigate("plan_screen") }
            FooterButton(text = "Stats") { navController.navigate("stat_screen") }
        }
    }
}

@Composable
fun FooterButton(text: String, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(110.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainApp()
}