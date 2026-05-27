package co.uk.gokul.letscook.feature.home.domain.model

/**
 * Domain model representing a list of ingredients.
 *
 * @property ingredients List of ingredients.
 */
data class Ingredients(val ingredients: List<Ingredient>)

/**
 * Domain model representing a single ingredient.
 *
 * @property idIngredient Unique identifier for the ingredient.
 * @property strIngredient Name of the ingredient.
 * @property strDescription Optional description of the ingredient.
 * @property strType Optional type of the ingredient.
 */
data class Ingredient(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String? = null,
    val strType: String? = null,
)