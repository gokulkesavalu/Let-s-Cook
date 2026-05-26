package co.uk.gokul.letscook.core.network.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object for the categories response.
 *
 * @property categories List of meal categories.
 */
@Serializable
data class CategoriesResponse(
    val categories: List<Category>,
)

/**
 * Data transfer object for a single category.
 *
 * @property idCategory Unique identifier for the category.
 * @property strCategory Name of the category.
 * @property strCategoryDescription Description of the category.
 * @property strCategoryThumb Thumbnail URL for the category.
 */
@Serializable
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String,
)
