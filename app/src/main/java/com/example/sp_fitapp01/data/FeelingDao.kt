package com.example.sp_fitapp01.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FeelingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFeeling(feeling: Feeling)

    @Update
    suspend fun updateFeeling(feeling: Feeling)

    @Delete
    suspend fun deleteFeeling(feeling: Feeling)

    @Query("SELECT * from feelings WHERE value = :value")
    fun getFeeling(value: Int): Flow<Feeling>

    @Query("SELECT * from feelings ORDER BY name ASC")
    fun getAllFeelings(): Flow<List<Feeling>>

    @Query("SELECT * from feelings WHERE value = :value")
    fun getFeelingsByValue(value: Int): Flow<List<Feeling>>

    @Query("SELECT COUNT(*) FROM feelings")
    fun getTotalWorkouts(): Flow<Int>
}