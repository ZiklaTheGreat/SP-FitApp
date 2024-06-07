package com.example.sp_fitapp01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sp_fitapp01.ui.theme.SPFitApp01Theme

/**
 * Entry point of the Fitness Application.
 * Sets up the UI using Jetpack Compose.
 */
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is starting.
     * Sets the content to the main UI.
     *
     * @param savedInstanceState Data supplied during previous instance, or null if not available.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SPFitApp01Theme {
                MainApp()
            }
        }
    }
}
