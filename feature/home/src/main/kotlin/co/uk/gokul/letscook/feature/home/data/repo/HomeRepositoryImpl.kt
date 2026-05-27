package co.uk.gokul.letscook.feature.home.data.repo

import co.uk.gokul.letscook.core.database.dao.AreaDao
import co.uk.gokul.letscook.core.database.dao.CategoryDao
import co.uk.gokul.letscook.core.database.dao.IngredientDao
import co.uk.gokul.letscook.core.network.api.MealsService
import co.uk.gokul.letscook.feature.home.data.mapper.toDomain
import co.uk.gokul.letscook.feature.home.data.mapper.toEntity
import co.uk.gokul.letscook.feature.home.domain.model.Areas
import co.uk.gokul.letscook.feature.home.domain.model.Categories
import co.uk.gokul.letscook.feature.home.domain.model.Ingredients
import co.uk.gokul.letscook.feature.home.domain.repo.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implementation of [HomeRepository] that fetches data from [MealsService] with local caching.
 *
 * This repository implements a "Cache-First" strategy where data is served from the local database
 * if it exists and hasn't expired. Otherwise, fresh data is fetched from the network,
 * saved to the database, and then returned.
 *
 * @property mealsService The API service used to fetch meal data from the network.
 * @property categoryDao Data Access Object for category-related database operations.
 * @property areaDao Data Access Object for area-related database operations.
 * @property ingredientDao Data Access Object for ingredient-related database operations.
 * @property dbScope CoroutineScope used for background database operations (like saving fresh network data).
 */
class HomeRepositoryImpl @Inject constructor(
    private val mealsService: MealsService,
    private val categoryDao: CategoryDao,
    private val areaDao: AreaDao,
    private val ingredientDao: IngredientDao,
    private val dbScope: CoroutineScope,
) : HomeRepository {
    companion object {
        /**
         * Duration (in milliseconds) for which the local cache is considered valid (15 minutes).
         */
        const val CACHE_TIMEOUT = 15 * 60 * 1000L
    }

    /**
     * Retrieves categories, using local cache if valid.
     */
    override suspend fun getCategories(): Result<Categories> {
        val cachedCategories = categoryDao.getCategories()
        val isCacheValid = cachedCategories.isNotEmpty() &&
                (System.currentTimeMillis() - cachedCategories.first().cachedAt < CACHE_TIMEOUT)
        if (isCacheValid) {
            return Result.success(Categories(categories = cachedCategories.map { it.toDomain() }))
        }
        return try {
            val categories = mealsService.getCategories()
            dbScope.launch {
                categoryDao.addCategory(categories.categories.map { it.toEntity() })
            }
            Result.success(categories.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Retrieves areas, using local cache if valid.
     */
    override suspend fun getAreas(): Result<Areas> {
        val cachedAreas = areaDao.getAreas()
        val isCacheValid = cachedAreas.isNotEmpty() &&
                (System.currentTimeMillis() - cachedAreas.first().cachedAt < CACHE_TIMEOUT)
        if (isCacheValid) {
            return Result.success(Areas(areas = cachedAreas.map { it.toDomain() }))
        }
        return try {
            val areas = mealsService.getAreas()
            dbScope.launch {
                areaDao.addAreas(areas.areas.map { it.toEntity() })
            }
            Result.success(areas.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Retrieves ingredients, using local cache if valid.
     */
    override suspend fun getIngredients(): Result<Ingredients> {
        val cachedIngredients = ingredientDao.getIngredients()
        val isCacheValid = cachedIngredients.isNotEmpty() &&
                (System.currentTimeMillis() - cachedIngredients.first().cachedAt < CACHE_TIMEOUT)
        if (isCacheValid) {
            return Result.success(Ingredients(ingredients = cachedIngredients.map { it.toDomain() }))
        }
        return try {
            val ingredients = mealsService.getIngredients()
            dbScope.launch {
                ingredientDao.addIngredients(ingredients.ingredients.map { it.toEntity() })
            }
            Result.success(ingredients.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}