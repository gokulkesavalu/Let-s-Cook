package co.uk.gokul.letscook.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.uk.gokul.letscook.core.database.entities.IngredientEntity

/**
 * Data Access Object for the [IngredientEntity].
 */
@Dao
interface IngredientDao {

    /**
     * Inserts a list of ingredients into the database.
     * Replaces existing entries on conflict.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIngredients(ingredients: List<IngredientEntity>)

    /**
     * Retrieves all ingredients from the database, ordered by ID.
     */
    @Query("SELECT * FROM ingredients ORDER BY idIngredient ASC")
    suspend fun getIngredients(): List<IngredientEntity>
}