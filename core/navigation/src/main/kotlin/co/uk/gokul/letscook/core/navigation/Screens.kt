package co.uk.gokul.letscook.core.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes for the application.
 */
sealed interface Screen {
    
    /**
     * The home screen showing categories, areas, and ingredients.
     */
    @Serializable
    data object Home : Screen

    /**
     * Screen displaying meals for a specific category, area, or ingredient.
     * 
     * @property title The title to display on the screen.
     * @property filterType The type of filter (e.g., "c", "a", "i").
     * @property filterValue The value of the filter (e.g., "Seafood").
     */
    @Serializable
    data class Meals(
        val title: String,
        val filterType: String,
        val filterValue: String
    ) : Screen

    /**
     * Detailed view of a single meal.
     * 
     * @property mealId Unique identifier of the meal.
     */
    @Serializable
    data class MealDetails(
        val mealId: String
    ) : Screen
}
