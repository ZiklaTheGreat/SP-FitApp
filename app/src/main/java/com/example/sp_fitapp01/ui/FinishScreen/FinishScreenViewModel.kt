package com.example.sp_fitapp01.ui.FinishScreen

import androidx.lifecycle.ViewModel
import com.example.sp_fitapp01.data.Feeling
import com.example.sp_fitapp01.data.FeelingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class FeelingDetails(
    val name: String = "Exhausted",
    val id: Int = 1
)

class FinishScreenViewModel(private val feelingRepository: FeelingRepository) : ViewModel() {
    private val _selectedFeeling = MutableStateFlow(FeelingDetails())
    val selectedFeeling: StateFlow<FeelingDetails> = _selectedFeeling.asStateFlow()

    fun setSelectedFeel(feel: Int, pName: String) {
        _selectedFeeling.value = FeelingDetails(name = pName, id = feel)
    }

    suspend fun saveFeeling() {
        feelingRepository.insertFeeling(selectedFeeling.value.toFeeling())
    }
}

fun FeelingDetails.toFeeling(): Feeling {
    return Feeling(name = this.name, value = this.id)
}