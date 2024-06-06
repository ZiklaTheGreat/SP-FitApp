package com.example.sp_fitapp01.data

import android.content.Context

/**
 * Interface for providing access to application-wide dependencies.
 */
interface AppContainer {
    val feelingRepository: FeelingRepository
}

/**
 * Implementation of [AppContainer] providing access to application-wide dependencies.
 *
 * @param context The application context for initializing dependencies.
 */
class AppDataContainer(private val context: Context) : AppContainer {

    override val feelingRepository: FeelingRepository by lazy {
        OfflineFeelingRepository(FeelingDatabase.getDatabase(context).feelingDao())
    }
}
