package com.example.sp_fitapp01.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Feeling::class], version = 1, exportSchema = false)
abstract class FeelingDatabase : RoomDatabase() {

    abstract fun feelingDao(): FeelingDao

    companion object {
        @Volatile
        private var Instance: FeelingDatabase? = null

        fun getDatabase(context: Context): FeelingDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FeelingDatabase::class.java, "feeling_database")
                    .build().also { Instance = it }
            }
        }
    }
}