package co.uk.gokul.letscook.core.network.api

import co.uk.gokul.letscook.core.network.dto.CategoriesResponse
import co.uk.gokul.letscook.core.network.dto.IngredientsResponse
import retrofit2.http.GET

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
    suspend fun getIngredients() : IngredientsResponse

}
