package com.example.sp_fitapp01.ui.WorkoutScreen

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.PlansScreen.Plan
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel for managing workout data and logic.
 *
 * @param context Application context for accessing resources.
 */
class WorkoutViewModel(private val context: Context) : ViewModel() {

    private val _currentExerciseIndex = mutableStateOf(0)
    val currentExerciseIndex: State<Int> = _currentExerciseIndex

    private val _timeLeft = mutableStateOf(30)
    val timeLeft: State<Int> = _timeLeft

    private val _isResting = mutableStateOf(false)
    val isResting: State<Boolean> = _isResting

    private val _isOver = mutableStateOf(false)
    val isOver: State<Boolean> = _isOver

    private var plan: Plan? = null
    private var timerJob: Job? = null

    /**
     * Sets the workout plan and starts the timer.
     *
     * @param newPlan The new workout plan to be set.
     */
    fun setPlan(newPlan: Plan) {
        plan = newPlan
        startTimer()
    }

    /**
     * Switches to the next phase of the workout (exercise or rest period).
     */
    private fun switchToNextPhase() {
        if (plan == null) return

        if (_currentExerciseIndex.value == plan!!.exercises.size - 1 && !_isResting.value) {
            playSound(R.raw.finish)
            _isOver.value = true
        } else {
            if (_isResting.value) {
                _currentExerciseIndex.value++
                _isResting.value = false
                _timeLeft.value = 30
            } else {
                _isResting.value = true
                _timeLeft.value = 10
            }
            startTimer()
        }
    }

    /**
     * Starts the timer for the current exercise or rest period.
     */
    private fun startTimer() {
        playSound(R.raw.notification)
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timeLeft.value > 0) {
                delay(1000L)
                _timeLeft.value -= 1
            }
            switchToNextPhase()
        }
    }

    /**
     * Skips the current phase of the workout.
     */
    fun skip() {
        timerJob?.cancel()
        _timeLeft.value = 0
        switchToNextPhase()
    }

    /**
     * Plays a sound for a given resource ID.
     *
     * @param soundId The resource ID of the sound to be played.
     */
    private fun playSound(soundId: Int) {
        val mediaPlayer = MediaPlayer.create(context, soundId)
        mediaPlayer.start()
    }
}

