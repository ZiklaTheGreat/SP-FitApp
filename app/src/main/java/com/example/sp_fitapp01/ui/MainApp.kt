package com.example.sp_fitapp01.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.sp_fitapp01.ui.navigation.FitAppNavigation

/**
 * Main UI entry point for the Fitness Application.
 * This composable function sets up the navigation within the application.
 */
@Composable
fun MainApp() {
    FitAppNavigation(navController = rememberNavController())
}
