package com.example.sp_fitapp01.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sp_fitapp01.FitnessApplication
import com.example.sp_fitapp01.ui.FinishScreen.FinishScreenViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FinishScreenViewModel(fitnessApplication().container.feelingRepository)
        }
    }
}

//class TempWorkoutViewModelFactory(private val onFinish: () -> Unit) : ViewModelProvider.Factory {
//    val BigFactory = viewModelFactory {
////        initializer {
//            //WorkoutViewModel(onFinish = onFinish)
////        }
//    }
//}

//class WorkoutViewModelFactory(private val onFinish: () -> Unit) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return WorkoutViewModel(onFinish) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

fun CreationExtras.fitnessApplication(): FitnessApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FitnessApplication)