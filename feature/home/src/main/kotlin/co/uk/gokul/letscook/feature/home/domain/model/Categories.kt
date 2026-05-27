package co.uk.gokul.letscook.feature.home.domain.model

/**
 * Domain model representing a list of meal categories.
 *
 * @property categories List of meal categories.
 */
data class Categories(val categories: List<Category>)

/**
 * Domain model representing a single meal category.
 *
 * @property idCategory Unique identifier for the category.
 * @property strCategory Name of the category.
 * @property strCategoryDescription Description of the category.
 * @property strCategoryThumb URL to the category's thumbnail image.
 */
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String,
)