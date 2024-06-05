package com.example.sp_fitapp01.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feelings")
data class Feeling(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: Int
)