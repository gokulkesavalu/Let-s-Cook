package co.uk.gokul.letscook.feature.meals.domain.repo

import android.os.Parcelable
import co.uk.gokul.letscook.feature.meals.domain.model.Meals
import kotlinx.parcelize.Parcelize

/**
 * Repository interface for fetching meal-related data.
 *
 * This repository is responsible for providing lists of meals filtered by
 * categories, areas, or ingredients, implementing an offline-first caching strategy.
 */
interface MealsRepository {
    /**
     * Retrieves a list of meals based on the specified [filter].
     *
     * @param filter The filter to apply (Category, Area, or Ingredient).
     * @return A [Result] containing the [Meals] domain model.
     */
    suspend fun getMeals(filter: MealFilter): Result<Meals>
}

/**
 * Sealed interface representing the different types of filters that can be applied to meals.
 *
 * Implements [Parcelable] to support state preservation across process death.
 */
sealed interface MealFilter : Parcelable {
    /**
     * Filter meals by category (e.g., "Seafood").
     *
     * @property value The name of the category.
     */
    @Parcelize
    data class Category(val value: String) : MealFilter

    /**
     * Filter meals by geographic area/cuisine (e.g., "Canadian").
     *
     * @property value The name of the area.
     */
    @Parcelize
    data class Area(val value: String) : MealFilter

    /**
     * Filter meals by main ingredient (e.g., "Chicken").
     *
     * @property value The name of the ingredient.
     */
    @Parcelize
    data class Ingredient(val value: String) : MealFilter
}

