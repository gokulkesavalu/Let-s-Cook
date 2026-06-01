package co.uk.gokul.letscook.feature.mealdetails.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.uk.gokul.letscook.feature.mealdetails.domain.model.MealDetails
import co.uk.gokul.letscook.feature.mealdetails.domain.repo.MealDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MealDetailsViewModel @Inject constructor(
    private val mealDetailsRepository: MealDetailsRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _idMeal = savedStateHandle.getStateFlow("idMeal", "")

    val uiState: StateFlow<MealDetailsUiState> =
        _idMeal.flatMapLatest { idMeal ->
            flow {
                emit(MealDetailsUiState.Loading)
                mealDetailsRepository.getMealsById(idMeal = idMeal)
                    .onSuccess { mealDetails ->
                        emit(MealDetailsUiState.Success(mealDetails = mealDetails))
                    }
                    .onFailure { exception ->
                        emit(MealDetailsUiState.Error(exception.message ?: "Something went wrong"))
                    }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MealDetailsUiState.Loading
        )

    fun setMealId(idMeal: String) {
        savedStateHandle["idMeal"] = idMeal
    }
}

sealed interface MealDetailsUiState {
    data object Loading : MealDetailsUiState
    data class Success(val mealDetails: MealDetails) : MealDetailsUiState
    data class Error(val error: String?) : MealDetailsUiState
}