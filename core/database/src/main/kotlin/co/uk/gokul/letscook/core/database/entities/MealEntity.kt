package co.uk.gokul.letscook.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a detailed meal.
 *
 * This entity stores comprehensive information about a meal, including its
 * instructions, ingredients, measures, and metadata as returned by TheMealDB API.
 *
 * @property idMeal Unique identifier for the meal.
 * @property strMeal Name of the meal.
 * @property strCategory Category of the meal (e.g., "Seafood").
 * @property strArea Geographical area of the meal (e.g., "British").
 * @property strInstructions Preparation instructions.
 * @property strMealThumb URL to the meal's thumbnail image.
 * @property strTags Comma-separated tags associated with the meal.
 * @property strYoutube URL to a YouTube video of the meal preparation.
 * @property strSource URL to the original source of the recipe.
 */
@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = false) val idMeal: String,
    val strMeal: String,
    val strArea: String,
    val strCategory: String,
    val strCountry: String,
    val strMealThumb: String,
    val strCreativeCommonsConfirmed: String? = null,
    val strImageSource: String?,
    val strInstructions: String?,
    val strMealAlternate: String? = null,
    val strSource: String?,
    val strTags: String?,
    val strYoutube: String?,
    val dateModified: String? = null,
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient2: String?,
    val strIngredient20: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?,
    val cachedAt: Long = System.currentTimeMillis()
)
