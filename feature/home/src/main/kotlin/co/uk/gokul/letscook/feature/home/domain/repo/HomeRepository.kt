package co.uk.gokul.letscook.feature.home.domain.repo

import co.uk.gokul.letscook.feature.home.domain.model.Areas
import co.uk.gokul.letscook.feature.home.domain.model.Categories
import co.uk.gokul.letscook.feature.home.domain.model.Ingredients

/**
 * Repository interface for home-related data operations.
 */
interface HomeRepository {

    /**
     * Retrieves the list of meal categories.
     */
    suspend fun getCategories(): Categories

    /**
     * Retrieves the list of geographic areas.
     */
    suspend fun getAreas(): Areas

    /**
     * Retrieves the list of ingredients.
     */
    suspend fun getIngredients(): Ingredients

}