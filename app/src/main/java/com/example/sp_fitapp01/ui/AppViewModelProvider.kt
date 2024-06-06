package com.example.sp_fitapp01.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sp_fitapp01.FitnessApplication
import com.example.sp_fitapp01.ui.FinishScreen.FinishScreenViewModel
import com.example.sp_fitapp01.ui.StatScreen.StatViewModel
import com.example.sp_fitapp01.ui.WorkoutScreen.WorkoutViewModel


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
//class WorkoutViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
//            return WorkoutViewModel(context) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}


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