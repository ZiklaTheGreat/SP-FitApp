package com.example.sp_fitapp01.data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sp_fitapp01.ui.ExercisesScreen.ExerciseDetailScreen
import com.example.sp_fitapp01.ui.ExercisesScreen.ExerciseListScreen
import com.example.sp_fitapp01.ui.FinishScreen.FinishScreen
import com.example.sp_fitapp01.ui.HomeScreen.MainScreen
import com.example.sp_fitapp01.ui.PlansScreen.PlanDetailScreen
import com.example.sp_fitapp01.ui.PlansScreen.PlansScreen
import com.example.sp_fitapp01.ui.StatScreen.StatScreen
import com.example.sp_fitapp01.ui.WorkoutScreen.WorkoutScreen

/**
 * Composable function for setting up navigation within the FitApp.
 *
 * @param navController The navigation controller for managing navigation within the app.
 * @param modifier The modifier to apply to the NavHost.
 */
@Composable
fun FitAppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = "main_screen", modifier = modifier
    ) {
        composable("main_screen") { MainScreen(navController) }
        composable("exercise_screen") { ExerciseListScreen(navController) }
        composable(
            "exercise_detail_screen/{exerciseID}",
            arguments = listOf(navArgument("exerciseID") { type = NavType.StringType })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getString("exerciseID") ?: ""
            ExerciseDetailScreen(
                exerciseName = exerciseId,
                navController = navController)
        }
        composable("plan_screen") { PlansScreen(navController) }
        composable(
            "plan_detail_screen/{planId}",
            arguments = listOf(navArgument("planId") { type = NavType.StringType })
        ) { backStackEntry ->
            val planId = backStackEntry.arguments?.getString("planId") ?: ""
            PlanDetailScreen(
                navController = navController,
                planName = planId
            )
        }
        composable("finish_screen") { FinishScreen(navController) }
        composable("stat_screen") { StatScreen(navController) }
        composable(
            "workout_screen/{planId}",
            arguments = listOf(navArgument("planId") { type = NavType.StringType })
        ) { backStackEntry ->
            val planId = backStackEntry.arguments?.getString("planId") ?: ""
            WorkoutScreen(
                navController = navController,
                planId = planId,
                onWorkoutComplete = { navController.navigate("finish_screen") })
        }
    }
}

