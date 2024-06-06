package com.example.sp_fitapp01.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sp_fitapp01.FitnessApplication
import com.example.sp_fitapp01.ui.AppViewModelProvider.Factory
import com.example.sp_fitapp01.ui.FinishScreen.FinishScreenViewModel
import com.example.sp_fitapp01.ui.StatScreen.StatViewModel


/**
 * Provides view models for the Fitness Application.
 * The [Factory] object provides a ViewModelFactory for creating various view models.
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FinishScreenViewModel(fitnessApplication().container.feelingRepository)
        }
        initializer {
            StatViewModel(fitnessApplication().container.feelingRepository)
        }
    }
}

/**
 * Retrieves the Fitness Application instance from creation extras.
 *
 * @return The Fitness Application instance.
 */
fun CreationExtras.fitnessApplication(): FitnessApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FitnessApplication)
