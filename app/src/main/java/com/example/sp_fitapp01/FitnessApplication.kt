package com.example.sp_fitapp01

import android.app.Application
import com.example.sp_fitapp01.data.databse.AppContainer
import com.example.sp_fitapp01.data.databse.AppDataContainer

/**
 * Application class for the Fitness Application.
 * This class initializes the application's dependency injection container [container]
 * on application startup.
 */
class FitnessApplication : Application() {
    lateinit var container: AppContainer

    /**
     * Called when the application is starting.
     * Initializes the [container] by creating an instance of [AppDataContainer] and assigning it.
     */
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
