package com.example.sp_fitapp01.data

import kotlinx.coroutines.flow.Flow

interface FeelingRepository {
    suspend fun insertFeeling(feeling: Feeling)
    suspend fun deleteFeeling(feeling: Feeling)
    suspend fun updateFeeling(feeling: Feeling)
    fun getFeeling(value: Int): Flow<Feeling>
    fun getAllFeelings(): Flow<List<Feeling>>
    fun getFeelingByValue(value: Int): Flow<List<Feeling>>
    fun getTotalWorkouts(): Flow<Int>
}