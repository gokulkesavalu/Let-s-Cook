package co.uk.gokul.letscook.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.uk.gokul.letscook.core.database.dao.AreaDao
import co.uk.gokul.letscook.core.database.dao.CategoryDao
import co.uk.gokul.letscook.core.database.dao.IngredientDao
import co.uk.gokul.letscook.core.database.entities.AreaEntity
import co.uk.gokul.letscook.core.database.entities.CategoryEntity
import co.uk.gokul.letscook.core.database.entities.IngredientEntity

/**
 * Main database for the Let's Cook application.
 *
 * This database provides access to categories, areas, and ingredients.
 */
@Database(
    entities = [AreaEntity::class, CategoryEntity::class, IngredientEntity::class],
    version = 1
)
abstract class LetsCookDatabase : RoomDatabase() {
    /**
     * Provides access to [CategoryDao] for category-related operations.
     */
    abstract fun categoriesDao(): CategoryDao

    /**
     * Provides access to [AreaDao] for area-related operations.
     */
    abstract fun areasDao(): AreaDao

    /**
     * Provides access to [IngredientDao] for ingredient-related operations.
     */
    abstract fun ingredientsDao(): IngredientDao
}