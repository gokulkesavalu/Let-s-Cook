package co.uk.gokul.letscook.feature.meals.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.uk.gokul.letscook.feature.meals.domain.model.Meals
import co.uk.gokul.letscook.feature.meals.domain.repo.MealFilter
import co.uk.gokul.letscook.feature.meals.domain.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel for the Meals screen.
 *
 * This ViewModel handles the logic for fetching and displaying a list of meals
 * based on a provided [MealFilter]. It uses [SavedStateHandle] to persist the filter
 * across process death.
 *
 * @property mealsRepository The repository used to fetch meal data.
 * @property savedStateHandle Handle to saved state for state preservation.
 */
@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MealsViewModel @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _filter = savedStateHandle.getStateFlow<MealFilter?>("filter", null)

    init {
        if (_filter.value == null) {
            val filterType = savedStateHandle.get<String>("filterType")
            val filterValue = savedStateHandle.get<String>("filterValue")
            if (filterType != null && filterValue != null) {
                val initialFilter = when (filterType) {
                    "c" -> MealFilter.Category(filterValue)
                    "a" -> MealFilter.Area(filterValue)
                    "i" -> MealFilter.Ingredient(filterValue)
                    else -> null
                }
                initialFilter?.let { setFilter(it) }
            }
        }
    }

    /**
     * The current UI state of the meals screen, reactive to changes in the filter.
     */
    val uiState: StateFlow<MealsUiState> = _filter
        .filterNotNull()
        .distinctUntilChanged()
        .flatMapLatest { filter ->
            flow {
                emit(MealsUiState.Loading)
                mealsRepository.getMeals(filter)
                    .onSuccess { meals ->
                        if (meals.meals.isNotEmpty()) {
                            emit(MealsUiState.Success(meals = meals))
                        } else {
                            emit(MealsUiState.EmptyResults)
                        }
                    }
                    .onFailure { exception ->
                        emit(MealsUiState.Error(exception.message))
                    }
            }.catch { exception ->
                emit(MealsUiState.Error(exception.message))
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MealsUiState.Loading
        )

    /**
     * Updates the current meal filter.
     *
     * @param filter The new filter to apply.
     */
    fun setFilter(filter: MealFilter) {
        savedStateHandle["filter"] = filter
    }
}

/**
 * Represents the different UI states for the Meals screen.
 */
sealed interface MealsUiState {
    /**
     * Indicates that meal data is currently being loaded.
     */
    data object Loading : MealsUiState

    /**
     * Indicates that the fetch was successful but returned no meals.
     */
    data object EmptyResults : MealsUiState

    /**
     * Indicates a successful fetch with a list of meals.
     *
     * @property meals The list of meals retrieved.
     */
    data class Success(val meals: Meals) : MealsUiState

    /**
     * Indicates that an error occurred while fetching meal data.
     *
     * @property message An optional error message.
     */
    data class Error(val message: String?) : MealsUiState
}
