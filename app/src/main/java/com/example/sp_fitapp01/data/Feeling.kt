package com.example.sp_fitapp01.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a feeling entity.
 * @param id The unique identifier for the feeling (auto-generated).
 * @param name The name of the feeling.
 * @param value The numerical value representing the feeling.
 */
@Entity(tableName = "feelings")
data class Feeling(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: Int
)