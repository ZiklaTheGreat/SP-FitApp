package com.example.sp_fitapp01.data

import kotlinx.coroutines.flow.Flow

class OfflineFeelingRepository(private val feelingDao: FeelingDao) : FeelingRepository {
    override suspend fun insertFeeling(feeling: Feeling) = feelingDao.insertFeeling(feeling)
    override suspend fun updateFeeling(feeling: Feeling) = feelingDao.updateFeeling(feeling)
    override suspend fun deleteFeeling(feeling: Feeling) = feelingDao.deleteFeeling(feeling)
    override fun getFeeling(value: Int): Flow<Feeling> = feelingDao.getFeeling(value)
    override fun getAllFeelings(): Flow<List<Feeling>> = feelingDao.getAllFeelings()
    override fun getFeelingByValue(value: Int): Flow<List<Feeling>> = feelingDao.getFeelingsByValue(value)
    override fun getTotalWorkouts(): Flow<Int> = feelingDao.getTotalWorkouts()
}