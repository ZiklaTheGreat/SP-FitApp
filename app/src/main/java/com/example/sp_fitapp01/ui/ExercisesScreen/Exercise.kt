package com.example.sp_fitapp01.ui.ExercisesScreen

import com.example.sp_fitapp01.R

/**
 * Represents an exercise in the Fitness Application.
 *
 * @property imageResId The resource ID of the static image representing the exercise.
 * @property gifResourceId The resource ID of the GIF representing the exercise.
 * @property name The name of the exercise.
 * @property description A brief description of the exercise.
 */
data class Exercise(
    val imageResId: Int,
    val gifResourceId: Int,
    val name: String,
    val description: String
)

/**
 * Returns a list of default exercises.
 *
 * @return A list of Exercise objects representing default exercises.
 */
fun defExercises(): List<Exercise> {
    return listOf(
        Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
        Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise..."),
        Exercise(R.drawable.sit_up, R.raw.sit_up, "Sit-up", "A sit-up is an abdominal endurance training..."),
        Exercise(R.drawable.dip, R.raw.dip, "Dip", "A dip is an upper-body strength exercise..."),
        Exercise(R.drawable.jumping_jack, R.raw.jumping_jack, "Jumping jack", "A jumping jack, also known as a star jump..."),
        Exercise(R.drawable.stretch, R.raw.stretch, "Stretch", "Stretching is a form of physical exercise...")
    )
}
