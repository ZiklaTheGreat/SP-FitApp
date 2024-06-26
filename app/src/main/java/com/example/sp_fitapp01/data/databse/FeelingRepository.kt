package com.example.sp_fitapp01.data.databse

import com.example.sp_fitapp01.data.Feeling
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing feelings data.
 */
interface FeelingRepository {
    suspend fun insertFeeling(feeling: Feeling)
    fun getFeelingByValue(value: Int): Flow<List<Feeling>>
    fun getTotalWorkouts(): Flow<Int>
}