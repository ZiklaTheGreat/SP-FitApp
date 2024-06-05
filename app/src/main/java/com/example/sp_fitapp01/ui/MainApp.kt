package com.example.sp_fitapp01.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.sp_fitapp01.ui.navigation.FitAppNavigation

@Composable
fun MainApp() {
    FitAppNavigation(navController = rememberNavController())
}