package co.uk.gokul.letscook.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.uk.gokul.letscook.core.database.entities.CategoryEntity

/**
 * Data Access Object for the [CategoryEntity].
 */
@Dao
interface CategoryDao {

    /**
     * Inserts a list of categories into the database.
     * Replaces existing entries on conflict.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(categories: List<CategoryEntity>)

    /**
     * Retrieves all categories from the database, ordered by ID.
     */
    @Query("SELECT * FROM categories ORDER BY idCategory ASC")
    suspend fun getCategories(): List<CategoryEntity>
}