package com.example.sp_fitapp01.data.databse

import com.example.sp_fitapp01.data.Feeling
import kotlinx.coroutines.flow.Flow

/**
 * Offline implementation of the FeelingRepository interface using Room database.
 * @param feelingDao The DAO for accessing feelings data.
 */
class OfflineFeelingRepository(private val feelingDao: FeelingDao) : FeelingRepository {
    override suspend fun insertFeeling(feeling: Feeling) = feelingDao.insertFeeling(feeling)
    override fun getFeelingByValue(value: Int): Flow<List<Feeling>> = feelingDao.getFeelingsByValue(value)
    override fun getTotalWorkouts(): Flow<Int> = feelingDao.getTotalWorkouts()
}