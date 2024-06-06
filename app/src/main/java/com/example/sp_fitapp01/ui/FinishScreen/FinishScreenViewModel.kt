package com.example.sp_fitapp01.ui.FinishScreen

import androidx.lifecycle.ViewModel
import com.example.sp_fitapp01.data.Feeling
import com.example.sp_fitapp01.data.FeelingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the finish screen of the Fitness Application.
 *
 * @property feelingRepository The repository for handling feeling data.
 */
class FinishScreenViewModel(private val feelingRepository: FeelingRepository) : ViewModel() {
    private val _selectedFeeling = MutableStateFlow(FeelingDetails())
    val selectedFeeling: StateFlow<FeelingDetails> = _selectedFeeling.asStateFlow()

    /**
     * Sets the selected feeling.
     *
     * @param feel The value representing the feeling.
     * @param pName The name of the feeling.
     */
    fun setSelectedFeel(feel: Int, pName: String) {
        _selectedFeeling.value = FeelingDetails(name = pName, id = feel)
    }

    /**
     * Saves the selected feeling into database.
     */
    suspend fun saveFeeling() {
        feelingRepository.insertFeeling(selectedFeeling.value.toFeeling())
    }
}

/**
 * Converts the [FeelingDetails] object to a [Feeling] object.
 *
 * @return The converted Feeling object.
 */
fun FeelingDetails.toFeeling(): Feeling {
    return Feeling(name = this.name, value = this.id)
}