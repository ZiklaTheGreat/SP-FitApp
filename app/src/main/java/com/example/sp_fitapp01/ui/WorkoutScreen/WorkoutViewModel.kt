package com.example.sp_fitapp01.ui.WorkoutScreen

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.PlansScreen.Plan
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//class WorkoutViewModel(onFinish: () -> Unit) : ViewModel() {
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

    fun setPlan(newPlan: Plan) {
        plan = newPlan
        startTimer()
    }

    private fun switchToNextPhase() {
        if (plan == null) return

        if (_currentExerciseIndex.value == plan!!.exercises.size - 1 && !_isResting.value) {
            //onFinish()
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

    fun skip() {
        timerJob?.cancel()
        _timeLeft.value = 0
        switchToNextPhase()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    private fun playSound(soundId: Int) {
        val mediaPlayer = MediaPlayer.create(context, soundId)
        mediaPlayer.start()
    }
}

