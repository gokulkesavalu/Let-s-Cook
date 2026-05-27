package co.uk.gokul.letscook.feature.home.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.uk.gokul.letscook.feature.home.domain.model.Area
import co.uk.gokul.letscook.feature.home.domain.model.Category
import co.uk.gokul.letscook.feature.home.domain.model.Ingredient
import co.uk.gokul.letscook.feature.home.domain.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * ViewModel for the Home screen.
 * Orchestrates fetching categories, areas, and ingredients in parallel.
 *
 * @property homeRepository The repository for home-related data.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    /**
     * Triggers parallel loading of categories, areas, and ingredients.
     */
    private fun loadHomeData() = viewModelScope.launch {
        supervisorScope {
            launch { loadCategories() }
            launch { loadAreas() }
            launch { loadIngredients() }
        }
    }

    /**
     * Loads meal categories from the repository.
     */
    private suspend fun loadCategories() {
        try {
            _uiState.update { it.copy(isCategoriesLoading = true) }
            homeRepository.getCategories()
                .onSuccess { categories ->
                    _uiState.update { it.copy(categories = categories.categories) }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            categoriesError = exception.message ?: "Something went wrong"
                        )
                    }
                }
        } finally {
            _uiState.update { it.copy(isCategoriesLoading = false) }
        }
    }

    /**
     * Loads geographic areas from the repository.
     */
    private suspend fun loadAreas() {
        try {
            _uiState.update { it.copy(isAreasLoading = true) }
            homeRepository.getAreas()
                .onSuccess { areas ->
                    _uiState.update { it.copy(areas = areas.areas) }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            areasError = exception.message ?: "Something went wrong"
                        )
                    }
                }
        } finally {
            _uiState.update { it.copy(isAreasLoading = false) }
        }
    }

    /**
     * Loads ingredients from the repository.
     */
    private suspend fun loadIngredients() {
        try {
            _uiState.update { it.copy(isIngredientsLoading = true) }
            homeRepository.getIngredients()
                .onSuccess { ingredients ->
                    _uiState.update {
                        it.copy(ingredients = ingredients.ingredients)
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(ingredientsError = exception.message ?: "Something went wrong")
                    }
                }
        } finally {
            _uiState.update { it.copy(isIngredientsLoading = false) }
        }
    }
}

/**
 * UI state for the Home screen.
 *
 * @property isCategoriesLoading Whether categories are currently being loaded.
 * @property categories List of meal categories.
 * @property categoriesError Error message if categories loading fails.
 * @property isAreasLoading Whether areas are currently being loaded.
 * @property areas List of geographic areas.
 * @property areasError Error message if areas loading fails.
 * @property isIngredientsLoading Whether ingredients are currently being loaded.
 * @property ingredients List of ingredients.
 * @property ingredientsError Error message if ingredients loading fails.
 */
@Immutable
data class HomeUiState(
    val isCategoriesLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val categoriesError: String? = null,
    val isAreasLoading: Boolean = false,
    val areas: List<Area> = emptyList(),
    val areasError: String? = null,
    val isIngredientsLoading: Boolean = false,
    val ingredients: List<Ingredient> = emptyList(),
    val ingredientsError: String? = null
) {
    /**
     * Returns true if any of the components are still loading.
     */
    val showLoading: Boolean get() = isCategoriesLoading || isAreasLoading || isIngredientsLoading
}
