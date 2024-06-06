package com.example.sp_fitapp01.ui.PlansScreen

import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.ExercisesScreen.Exercise

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
    return listOf(
        Plan("Horné telo", R.drawable.push_up, listOf(
            Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
            Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise...")
        )),
        Plan("Dolné telo", R.drawable.push_up, listOf(
            Exercise(R.drawable.push_up, R.raw.push_up, "Push up", "A push-up is a common strength training exercise..."),
            Exercise(R.drawable.squat, R.raw.squat, "Squat", "A squat is a strength exercise...")
        )),
    )
}