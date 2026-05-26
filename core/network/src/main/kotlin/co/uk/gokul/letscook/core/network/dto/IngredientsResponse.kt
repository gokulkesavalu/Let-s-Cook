package co.uk.gokul.letscook.core.network.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object for the ingredients' response.
 *
 * @property meals List of ingredients.
 */
@Serializable
data class IngredientsResponse(
    val meals: List<Ingredient>,
)

/**
 * Data transfer object for a single ingredient.
 *
 * @property idIngredient Unique identifier for the ingredient.
 * @property strIngredient Name of the ingredient.
 * @property strDescription Optional description of the ingredient.
 * @property strType Optional category type of the ingredient.
 */
@Serializable
data class Ingredient(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String? = null,
    val strType: String? = null,
)
