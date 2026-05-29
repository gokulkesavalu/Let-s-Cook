package co.uk.gokul.letscook.core.network.api

import co.uk.gokul.letscook.core.network.dto.AreasResponse
import co.uk.gokul.letscook.core.network.dto.CategoriesResponse
import co.uk.gokul.letscook.core.network.dto.IngredientsResponse
import co.uk.gokul.letscook.core.network.dto.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for TheMealDB API.
 */
interface MealsService {

    /**
     * Fetches all meal categories.
     *
     * @return [CategoriesResponse] containing the list of categories.
     */
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    /**
     * Fetches the list of all ingredients.
     *
     * @return [IngredientsResponse] containing the list of ingredients.
     */
    @GET("list.php?i=list")
    suspend fun getIngredients(): IngredientsResponse

    /**
     * Fetches the list of all areas (cuisines).
     *
     * @return [AreasResponse] containing the list of areas.
     */
    @GET("list.php?a=list")
    suspend fun getAreas(): AreasResponse

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
}
