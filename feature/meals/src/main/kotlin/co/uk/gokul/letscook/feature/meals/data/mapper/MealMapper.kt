package co.uk.gokul.letscook.feature.meals.data.mapper

import co.uk.gokul.letscook.core.database.entities.MealEntity
import co.uk.gokul.letscook.core.network.dto.MealDto
import co.uk.gokul.letscook.core.network.dto.MealsResponse
import co.uk.gokul.letscook.feature.meals.domain.model.Meal
import co.uk.gokul.letscook.feature.meals.domain.model.Meals

/**
 * Maps [MealsResponse] DTO to [Meals] domain model.
 *
 * @param category Optional category to populate in domain models.
 * @param area Optional area to populate in domain models.
 */
fun MealsResponse.toDomain(category: String? = null, area: String? = null) = Meals(
    meals = this.meals?.map { it.toDomain(category, area) } ?: emptyList()
)

/**
 * Maps [MealDto] DTO to [Meal] domain model.
 *
 * @param category Optional category to override the DTO value.
 * @param area Optional area to override the DTO value.
 */
fun MealDto.toDomain(category: String? = null, area: String? = null) = Meal(
    idMeal = this.idMeal,
    strArea = if (this.strArea.isBlank() && area != null) area else this.strArea,
    strCategory = if (this.strCategory.isBlank() && category != null) category else this.strCategory,
    strCountry = this.strCountry,
    strCreativeCommonsConfirmed = this.strCreativeCommonsConfirmed,
    strImageSource = this.strImageSource,
    strInstructions = this.strInstructions,
    strMeal = this.strMeal,
    strMealAlternate = this.strMealAlternate,
    strMealThumb = this.strMealThumb,
    strSource = this.strSource,
    strTags = this.strTags,
    strYoutube = this.strYoutube,
    dateModified = this.dateModified,
    strIngredient1 = this.strIngredient1,
    strIngredient2 = this.strIngredient2,
    strIngredient3 = this.strIngredient3,
    strIngredient4 = this.strIngredient4,
    strIngredient5 = this.strIngredient5,
    strIngredient6 = this.strIngredient6,
    strIngredient7 = this.strIngredient7,
    strIngredient8 = this.strIngredient8,
    strIngredient9 = this.strIngredient9,
    strIngredient10 = this.strIngredient10,
    strIngredient11 = this.strIngredient11,
    strIngredient12 = this.strIngredient12,
    strIngredient13 = this.strIngredient13,
    strIngredient14 = this.strIngredient14,
    strIngredient15 = this.strIngredient15,
    strIngredient16 = this.strIngredient16,
    strIngredient17 = this.strIngredient17,
    strIngredient18 = this.strIngredient18,
    strIngredient19 = this.strIngredient19,
    strIngredient20 = this.strIngredient20,
    strMeasure1 = this.strMeasure1,
    strMeasure2 = this.strMeasure2,
    strMeasure3 = this.strMeasure3,
    strMeasure4 = this.strMeasure4,
    strMeasure5 = this.strMeasure5,
    strMeasure6 = this.strMeasure6,
    strMeasure7 = this.strMeasure7,
    strMeasure8 = this.strMeasure8,
    strMeasure9 = this.strMeasure9,
    strMeasure10 = this.strMeasure10,
    strMeasure11 = this.strMeasure11,
    strMeasure12 = this.strMeasure12,
    strMeasure13 = this.strMeasure13,
    strMeasure14 = this.strMeasure14,
    strMeasure15 = this.strMeasure15,
    strMeasure16 = this.strMeasure16,
    strMeasure17 = this.strMeasure17,
    strMeasure18 = this.strMeasure18,
    strMeasure19 = this.strMeasure19,
    strMeasure20 = this.strMeasure20,
)

/**
 * Maps [MealDto] DTO to [MealEntity] database entity.
 *
 * This function handles populating filter metadata (category/area) that may be missing
 * from the API's lightweight filtering responses.
 *
 * @param category Optional category to override the DTO value.
 * @param area Optional area to override the DTO value.
 */
fun MealDto.toEntity(category: String? = null, area: String? = null) = MealEntity(
    idMeal = this.idMeal,
    strArea = if (this.strArea.isBlank() && area != null) area else this.strArea,
    strCategory = if (this.strCategory.isBlank() && category != null) category else this.strCategory,
    strCountry = this.strCountry,
    strCreativeCommonsConfirmed = this.strCreativeCommonsConfirmed,
    strImageSource = this.strImageSource,
    strInstructions = this.strInstructions,
    strMeal = this.strMeal,
    strMealAlternate = this.strMealAlternate,
    strMealThumb = this.strMealThumb,
    strSource = this.strSource,
    strTags = this.strTags,
    strYoutube = this.strYoutube,
    dateModified = this.dateModified,
    strIngredient1 = this.strIngredient1,
    strIngredient2 = this.strIngredient2,
    strIngredient3 = this.strIngredient3,
    strIngredient4 = this.strIngredient4,
    strIngredient5 = this.strIngredient5,
    strIngredient6 = this.strIngredient6,
    strIngredient7 = this.strIngredient7,
    strIngredient8 = this.strIngredient8,
    strIngredient9 = this.strIngredient9,
    strIngredient10 = this.strIngredient10,
    strIngredient11 = this.strIngredient11,
    strIngredient12 = this.strIngredient12,
    strIngredient13 = this.strIngredient13,
    strIngredient14 = this.strIngredient14,
    strIngredient15 = this.strIngredient15,
    strIngredient16 = this.strIngredient16,
    strIngredient17 = this.strIngredient17,
    strIngredient18 = this.strIngredient18,
    strIngredient19 = this.strIngredient19,
    strIngredient20 = this.strIngredient20,
    strMeasure1 = this.strMeasure1,
    strMeasure2 = this.strMeasure2,
    strMeasure3 = this.strMeasure3,
    strMeasure4 = this.strMeasure4,
    strMeasure5 = this.strMeasure5,
    strMeasure6 = this.strMeasure6,
    strMeasure7 = this.strMeasure7,
    strMeasure8 = this.strMeasure8,
    strMeasure9 = this.strMeasure9,
    strMeasure10 = this.strMeasure10,
    strMeasure11 = this.strMeasure11,
    strMeasure12 = this.strMeasure12,
    strMeasure13 = this.strMeasure13,
    strMeasure14 = this.strMeasure14,
    strMeasure15 = this.strMeasure15,
    strMeasure16 = this.strMeasure16,
    strMeasure17 = this.strMeasure17,
    strMeasure18 = this.strMeasure18,
    strMeasure19 = this.strMeasure19,
    strMeasure20 = this.strMeasure20,
)

/**
 * Maps [MealEntity] database entity to [Meal] domain model.
 */
fun MealEntity.toDomain() = Meal(
    idMeal = this.idMeal,
    strArea = this.strArea,
    strCategory = this.strCategory,
    strCountry = this.strCountry,
    strCreativeCommonsConfirmed = this.strCreativeCommonsConfirmed,
    strImageSource = this.strImageSource,
    strInstructions = this.strInstructions,
    strMeal = this.strMeal,
    strMealAlternate = this.strMealAlternate,
    strMealThumb = this.strMealThumb,
    strSource = this.strSource,
    strTags = this.strTags,
    strYoutube = this.strYoutube,
    dateModified = this.dateModified,
    strIngredient1 = this.strIngredient1,
    strIngredient2 = this.strIngredient2,
    strIngredient3 = this.strIngredient3,
    strIngredient4 = this.strIngredient4,
    strIngredient5 = this.strIngredient5,
    strIngredient6 = this.strIngredient6,
    strIngredient7 = this.strIngredient7,
    strIngredient8 = this.strIngredient8,
    strIngredient9 = this.strIngredient9,
    strIngredient10 = this.strIngredient10,
    strIngredient11 = this.strIngredient11,
    strIngredient12 = this.strIngredient12,
    strIngredient13 = this.strIngredient13,
    strIngredient14 = this.strIngredient14,
    strIngredient15 = this.strIngredient15,
    strIngredient16 = this.strIngredient16,
    strIngredient17 = this.strIngredient17,
    strIngredient18 = this.strIngredient18,
    strIngredient19 = this.strIngredient19,
    strIngredient20 = this.strIngredient20,
    strMeasure1 = this.strMeasure1,
    strMeasure2 = this.strMeasure2,
    strMeasure3 = this.strMeasure3,
    strMeasure4 = this.strMeasure4,
    strMeasure5 = this.strMeasure5,
    strMeasure6 = this.strMeasure6,
    strMeasure7 = this.strMeasure7,
    strMeasure8 = this.strMeasure8,
    strMeasure9 = this.strMeasure9,
    strMeasure10 = this.strMeasure10,
    strMeasure11 = this.strMeasure11,
    strMeasure12 = this.strMeasure12,
    strMeasure13 = this.strMeasure13,
    strMeasure14 = this.strMeasure14,
    strMeasure15 = this.strMeasure15,
    strMeasure16 = this.strMeasure16,
    strMeasure17 = this.strMeasure17,
    strMeasure18 = this.strMeasure18,
    strMeasure19 = this.strMeasure19,
    strMeasure20 = this.strMeasure20,
)