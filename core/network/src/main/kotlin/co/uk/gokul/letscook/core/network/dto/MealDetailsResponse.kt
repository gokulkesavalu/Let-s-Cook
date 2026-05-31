package co.uk.gokul.letscook.core.network.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object for the response containing detailed information about meals.
 *
 * @property meals The list of detailed meal information.
 */
@Serializable
data class MealDetailsResponse(
    val meals: List<MealDto>
)

/**
 * Data transfer object representing the full details of a meal.
 *
 * This class maps directly to the response structure of TheMealDB API.
 * It uses default values for optional fields to remain robust when handling
 * partial data from filter-based API endpoints.
 */
@Serializable
data class MealDto(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strArea: String = "",
    val strCategory: String = "",
    val strCountry: String = "",
    val strCreativeCommonsConfirmed: String? = null,
    val strImageSource: String? = null,
    val strInstructions: String? = null,
    val strMealAlternate: String? = null,
    val strSource: String? = null,
    val strTags: String? = null,
    val strYoutube: String? = null,
    val dateModified: String? = null,
    val strIngredient1: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null,
    val strIngredient16: String? = null,
    val strIngredient17: String? = null,
    val strIngredient18: String? = null,
    val strIngredient19: String? = null,
    val strIngredient2: String? = null,
    val strIngredient20: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,
    val strMeasure7: String? = null,
    val strMeasure8: String? = null,
    val strMeasure9: String? = null,
    val strMeasure10: String? = null,
    val strMeasure11: String? = null,
    val strMeasure12: String? = null,
    val strMeasure13: String? = null,
    val strMeasure14: String? = null,
    val strMeasure15: String? = null,
    val strMeasure16: String? = null,
    val strMeasure17: String? = null,
    val strMeasure18: String? = null,
    val strMeasure19: String? = null,
    val strMeasure20: String? = null,
)