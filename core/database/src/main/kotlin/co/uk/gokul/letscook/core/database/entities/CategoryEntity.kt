package co.uk.gokul.letscook.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a meal category.
 *
 * @property idCategory Unique identifier for the category.
 * @property strCategory Name of the category (e.g., "Beef", "Vegetarian").
 * @property strCategoryDescription Detailed description of the category.
 * @property strCategoryThumb URL to the category's thumbnail image.
 */
@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false) val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String,
)
