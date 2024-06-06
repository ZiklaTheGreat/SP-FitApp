package com.example.sp_fitapp01.ui.StatScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sp_fitapp01.data.FeelingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * ViewModel for statistics, containing data about feelings and workout count.
 *
 * @param feelingRepository Repository for manipulating feelings data.
 */
class StatViewModel(private val feelingRepository: FeelingRepository) : ViewModel() {
    private val _feelings = MutableStateFlow(emptyList<Float>())
    val feelings: StateFlow<List<Float>> = _feelings.asStateFlow()

    private val _totalWorkouts = MutableStateFlow(0)
    val totalWorkouts: StateFlow<Int> = _totalWorkouts.asStateFlow()

    init {
        viewModelScope.launch {
            val feelingCounts = (1..5).map { value ->
                feelingRepository.getFeelingByValue(value)
                    .map { feelings -> feelings.size.toFloat() }
                    .first()
            }
            _feelings.value = feelingCounts

            _totalWorkouts.value = feelingRepository.getTotalWorkouts()
                .first()
        }
    }
}