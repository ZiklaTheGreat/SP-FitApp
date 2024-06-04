package com.example.sp_fitapp01.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sp_fitapp01.FitnessApplication
import com.example.sp_fitapp01.ui.FinishScreen.FinishScreenViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory


object AppViewModelProvider {
    val Factory = viewModelFactory {
//        initializer {
//            FinishScreenViewModel(fitApp().container.feelingRepository)
//        }
        initializer {
            //Log.d("AppViewModelProvider", "Check 1")
            //println("Debug message 1")
           // val a = inventoryApplication()
            FinishScreenViewModel(inventoryApplication().container.feelingRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication(): FitnessApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FitnessApplication)