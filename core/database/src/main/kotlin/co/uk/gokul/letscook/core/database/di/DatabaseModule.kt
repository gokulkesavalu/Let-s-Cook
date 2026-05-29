package co.uk.gokul.letscook.core.database.di

import android.content.Context
import androidx.room.Room
import co.uk.gokul.letscook.core.database.LetsCookDatabase
import co.uk.gokul.letscook.core.database.dao.AreaDao
import co.uk.gokul.letscook.core.database.dao.CategoryDao
import co.uk.gokul.letscook.core.database.dao.IngredientDao
import co.uk.gokul.letscook.core.database.dao.MealDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for database-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the singleton instance of [LetsCookDatabase].
     */
    @Provides
    @Singleton
    fun provideGKStoresDatabase(@ApplicationContext app: Context): LetsCookDatabase =
        Room.databaseBuilder(
            app,
            LetsCookDatabase::class.java,
            "lets_cook_db"
        ).build()

    /**
     * Provides the [AreaDao] instance from the database.
     */
    @Provides
    @Singleton
    fun provideAreasDao(db: LetsCookDatabase) = db.areasDao()

    /**
     * Provides the [IngredientDao] instance from the database.
     */
    @Provides
    @Singleton
    fun provideIngredientsDao(db: LetsCookDatabase) = db.ingredientsDao()

    /**
     * Provides the [CategoryDao] instance from the database.
     */
    @Provides
    @Singleton
    fun provideCategoriesDao(db: LetsCookDatabase) = db.categoriesDao()

    /**
     * Provides the [MealDao] instance from the database.
     */
    @Provides
    @Singleton
    fun provideMealDao(db: LetsCookDatabase) = db.mealDao()
}