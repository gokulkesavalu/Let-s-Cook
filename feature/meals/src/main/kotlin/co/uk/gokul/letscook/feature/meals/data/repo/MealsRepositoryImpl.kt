package co.uk.gokul.letscook.feature.meals.data.repo

import co.uk.gokul.letscook.core.database.dao.MealDao
import co.uk.gokul.letscook.core.database.entities.MealEntity
import co.uk.gokul.letscook.core.network.api.MealsService
import co.uk.gokul.letscook.core.network.dto.MealsResponse
import co.uk.gokul.letscook.feature.meals.data.mapper.toDomain
import co.uk.gokul.letscook.feature.meals.data.mapper.toEntity
import co.uk.gokul.letscook.feature.meals.domain.model.Meals
import co.uk.gokul.letscook.feature.meals.domain.repo.MealFilter
import co.uk.gokul.letscook.feature.meals.domain.repo.MealsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implementation of [MealsRepository] that provides meal data filtered by various criteria.
 *
 * This repository implements an offline-first strategy using [MealsService] for remote data
 * and [MealDao] for local caching.
 *
 * @property mealsService The remote service for fetching meals.
 * @property mealDao The local DAO for meal persistence.
 * @property dbScope The scope used for background database operations.
 */
class MealsRepositoryImpl @Inject constructor(
    private val mealsService: MealsService,
    private val mealDao: MealDao,
    private val dbScope: CoroutineScope,
) : MealsRepository {

    companion object {
        /**
         * The duration for which the local cache is considered valid (15 minutes).
         */
        const val CACHE_TIMEOUT = 15 * 60 * 1000L
    }

    /**
     * Retrieves meals based on the provided [filter].
     *
     * @param filter The filter to apply (Category, Area, or Ingredient).
     * @return A [Result] containing the [Meals] domain model.
     */
    override suspend fun getMeals(
        filter: MealFilter,
    ): Result<Meals> {
        return when (filter) {
            is MealFilter.Category -> fetchData(
                fetchFromNetwork = { mealsService.getMealsByCategory(filter.value) },
                fetchFromDatabase = { mealDao.getMealsByCategory(filter.value) }
            )

            is MealFilter.Area -> fetchData(
                fetchFromNetwork = { mealsService.getMealsByArea(filter.value) },
                fetchFromDatabase = { mealDao.getMealsByArea(filter.value) }
            )

            is MealFilter.Ingredient -> fetchData(
                fetchFromNetwork = { mealsService.getMealsByIngredient(filter.value) },
                fetchFromDatabase = { mealDao.getMealsByIngredient(filter.value) }
            )
        }
    }

    /**
     * Common helper function to handle the cache-first logic.
     *
     * @param fetchFromNetwork Lambda to fetch data from the remote API.
     * @param fetchFromDatabase Lambda to fetch data from the local database.
     * @return A [Result] containing the [Meals] domain model.
     */
    private suspend fun fetchData(
        fetchFromNetwork: suspend () -> MealsResponse,
        fetchFromDatabase: suspend () -> List<MealEntity>
    ): Result<Meals> {
        val cachedMeals = fetchFromDatabase()
        if (isCacheValid(cachedMeals)) {
            return Result.success(Meals(meals = cachedMeals.map { it.toDomain() }))
        }

        return try {
            val networkResponse = fetchFromNetwork()
            dbScope.launch {
                mealDao.addMeals(networkResponse.meals.map { it.toEntity() })
            }
            Result.success(networkResponse.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Checks if the provided list of cached meals is valid based on the timeout.
     */
    private fun isCacheValid(cachedMeals: List<MealEntity>): Boolean {
        return cachedMeals.isNotEmpty() && (System.currentTimeMillis() - cachedMeals.first().cachedAt < CACHE_TIMEOUT)
    }
}