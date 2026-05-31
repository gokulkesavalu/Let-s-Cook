package co.uk.gokul.letscook.core.network.api

import co.uk.gokul.letscook.core.network.dto.MealDetailsResponse
import co.uk.gokul.letscook.core.network.dto.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for the Meals screen data.
 */
interface MealsService {
    /**
     * Fetches meals filtered by category.
     *
     * @param category The name of the category (e.g., "Seafood").
     * @return [MealsResponse] containing the list of meals.
     */
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealsResponse

    /**
     * Fetches meals filtered by area.
     *
     * @param area The name of the area/cuisine (e.g., "Canadian").
     * @return [MealsResponse] containing the list of meals.
     */
    @GET("filter.php")
    suspend fun getMealsByArea(@Query("a") area: String): MealsResponse

    /**
     * Fetches meals filtered by main ingredient.
     *
     * @param ingredient The name of the ingredient (e.g., "chicken_breast").
     * @return [MealsResponse] containing the list of meals.
     */
    @GET("filter.php")
    suspend fun getMealsByIngredient(@Query("i") ingredient: String): MealsResponse

    /**
     * Fetches detailed information for a specific meal by its ID.
     *
     * @param idMeal The unique identifier of the meal.
     * @return [MealDetailsResponse] containing the full details of the meal.
     */
    @GET("lookup.php")
    suspend fun getMealsById(@Query("i") idMeal: String): MealDetailsResponse
}
