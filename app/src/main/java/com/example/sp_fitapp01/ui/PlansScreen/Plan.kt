package com.example.sp_fitapp01.ui.PlansScreen

import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.ExercisesScreen.Exercise
import com.example.sp_fitapp01.ui.ExercisesScreen.defExercises

/**
 * Data class representing a workout plan.
 *
 * @property name The name of the workout plan.
 * @property imageResId The resource ID of the image representing the workout plan.
 * @property exercises The list of exercises included in the workout plan.
 */
data class Plan(val name: String,
                val imageResId: Int,
                val exercises: List<Exercise>
)

/**
 * Function providing dummy data for workout plans.
 *
 * @return A list of dummy workout plans.
 */
fun dummyPlans(): List<Plan> {
    val exercises = defExercises()
    val findExerciseByName: (String) -> Exercise? = { name -> exercises.find { it.name == name } }

    return listOf(
        Plan(
            "Horné telo",
            R.drawable.upper_body,
            listOfNotNull(
                findExerciseByName("Push up"),
                findExerciseByName("Dip"),
                findExerciseByName("Stretch")
            )
        ),
        Plan(
            "Dolné telo",
            R.drawable.lower_body,
            listOfNotNull(
                findExerciseByName("Jumping jack"),
                findExerciseByName("Squat"),
                findExerciseByName("Stretch")
            )
        ),
        Plan(
            "Jadro",
            R.drawable.core,
            listOfNotNull(
                findExerciseByName("Jumping jack"),
                findExerciseByName("Sit-up"),
                findExerciseByName("Stretch")
            )
        )
    )
}