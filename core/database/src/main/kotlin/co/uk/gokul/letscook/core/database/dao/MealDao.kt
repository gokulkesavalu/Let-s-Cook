package co.uk.gokul.letscook.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.uk.gokul.letscook.core.database.entities.MealEntity

/**
 * Data Access Object for the [MealEntity].
 *
 * Provides methods for caching and retrieving meal data from the local database.
 */
@Dao
interface MealDao {

    /**
     * Inserts a list of meals into the database.
     * Replaces existing entries with the same primary key (idMeal).
     *
     * @param meals The list of [MealEntity] objects to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeals(meals: List<MealEntity>)

    /**
     * Retrieves all cached meals belonging to a specific category.
     *
     * @param category The name of the category (e.g., "Beef").
     * @return A list of [MealEntity] objects, ordered by most recently cached.
     */
    @Query("SELECT * FROM meals WHERE strCategory = :category ORDER BY cachedAt DESC")
    suspend fun getMealsByCategory(category: String): List<MealEntity>

    /**
     * Retrieves all cached meals belonging to a specific area/cuisine.
     *
     * @param area The name of the area (e.g., "Italian").
     * @return A list of [MealEntity] objects, ordered by most recently cached.
     */
    @Query("SELECT * FROM meals WHERE strArea = :area ORDER BY cachedAt DESC")
    suspend fun getMealsByArea(area: String): List<MealEntity>

    /**
     * Searches for meals that contain the specified ingredient in any of the
     * 20 available ingredient slots.
     *
     * @param ingredient The name of the ingredient to search for.
     * @return A list of [MealEntity] objects containing the ingredient.
     */
    @Query("""
        SELECT * FROM meals 
        WHERE strIngredient1 = :ingredient OR strIngredient2 = :ingredient OR strIngredient3 = :ingredient 
        OR strIngredient4 = :ingredient OR strIngredient5 = :ingredient OR strIngredient6 = :ingredient 
        OR strIngredient7 = :ingredient OR strIngredient8 = :ingredient OR strIngredient9 = :ingredient 
        OR strIngredient10 = :ingredient OR strIngredient11 = :ingredient OR strIngredient12 = :ingredient 
        OR strIngredient13 = :ingredient OR strIngredient14 = :ingredient OR strIngredient15 = :ingredient 
        OR strIngredient16 = :ingredient OR strIngredient17 = :ingredient OR strIngredient18 = :ingredient 
        OR strIngredient19 = :ingredient OR strIngredient20 = :ingredient
        ORDER BY cachedAt DESC
    """)
    suspend fun getMealsByIngredient(ingredient: String): List<MealEntity>
}