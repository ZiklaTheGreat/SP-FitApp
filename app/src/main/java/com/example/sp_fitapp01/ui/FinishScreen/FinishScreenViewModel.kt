package com.example.sp_fitapp01.ui.FinishScreen

import androidx.lifecycle.ViewModel
import com.example.sp_fitapp01.data.Feeling
import com.example.sp_fitapp01.data.FeelingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class FeelingDetails(
    val name: String = "",
    val id: Int = 1
)

//class FinishScreenViewModel(private val feelingRepository: FeelingRepository) : ViewModel() {
//    private val _selectedFeeling = MutableStateFlow(FeelingDetails())
//    val selectedFeeling: StateFlow<FeelingDetails> = _selectedFeeling.asStateFlow()
//
//    fun setSelectedFeel(feel: Int, name: String) {
//        _selectedFeeling.value = FeelingDetails(name = name, id = feel)
//    }
//
//    fun getSelectedFeel(): Int {
//        return selectedFeeling.value.id
//    }
//
//    suspend fun saveFeeling() {
//        feelingRepository.insert(selectedFeeling.toFeelingDetails().toFeeling())
//    }
class FinishScreenViewModel(private val feelingRepository: FeelingRepository) : ViewModel() {
}

//fun StateFlow<FeelingDetails>.toFeelingDetails(): FeelingDetails {
//    return FeelingDetails(name = this.value.name, id = this.value.id)
//}
//
//fun FeelingDetails.toFeeling(): Feeling {
//    return Feeling(name = this.name, value = this.id)
//}