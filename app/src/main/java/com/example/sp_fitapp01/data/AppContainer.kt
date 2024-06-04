package com.example.sp_fitapp01.data

import android.content.Context

interface AppContainer {
    val feelingRepository: FeelingRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val feelingRepository: FeelingRepository by lazy {
        OfflineFeelingRepository(FeelingDatabase.getDatabase(context).feelingDao())
    }
}
