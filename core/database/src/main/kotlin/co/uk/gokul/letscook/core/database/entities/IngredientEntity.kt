package co.uk.gokul.letscook.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a meal ingredient.
 *
 * @property idIngredient Unique identifier for the ingredient.
 * @property strIngredient Name of the ingredient (e.g., "Chicken", "Salt").
 * @property strDescription Optional description of the ingredient.
 * @property strType Optional type or category of the ingredient.
 * @property cachedAt The timestamp when this entity was cached in the database.
 */
@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = false) val idIngredient: String,
    val strIngredient: String,
    val strDescription: String? = null,
    val strType: String? = null,
    val cachedAt: Long = System.currentTimeMillis()
)
