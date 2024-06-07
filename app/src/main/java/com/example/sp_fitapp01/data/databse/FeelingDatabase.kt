package com.example.sp_fitapp01.data.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sp_fitapp01.data.Feeling

/**
 * Room database for storing feelings data.
 */
@Database(entities = [Feeling::class], version = 1, exportSchema = false)
abstract class FeelingDatabase : RoomDatabase() {

    /**
     * Provides access to the DAO for feelings.
     * @return The FeelingDao instance.
     */
    abstract fun feelingDao(): FeelingDao

    companion object {
        @Volatile
        private var Instance: FeelingDatabase? = null

        /**
         * Retrieves the singleton instance of the FeelingDatabase.
         * @param context The application context for creating the database.
         * @return The FeelingDatabase instance.
         */
        fun getDatabase(context: Context): FeelingDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FeelingDatabase::class.java, "feeling_database")
                    .build().also { Instance = it }
            }
        }
    }
}