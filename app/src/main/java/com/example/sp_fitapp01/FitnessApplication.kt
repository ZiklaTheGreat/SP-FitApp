package com.example.sp_fitapp01

import android.app.Application
import com.example.sp_fitapp01.data.AppContainer
import com.example.sp_fitapp01.data.AppDataContainer

class FitnessApplication : Application() {


    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}