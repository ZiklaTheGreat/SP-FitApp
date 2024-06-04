package com.example.sp_fitapp01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sp_fitapp01.ui.MainApp
import com.example.sp_fitapp01.ui.theme.SPFitApp01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SPFitApp01Theme {
                MainApp()
                //ExerciseListScreen()
            }
        }
    }
}