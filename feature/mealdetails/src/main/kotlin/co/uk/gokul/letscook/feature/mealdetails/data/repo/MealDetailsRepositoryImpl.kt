package co.uk.gokul.letscook.feature.mealdetails.data.repo

import co.uk.gokul.letscook.core.database.dao.MealDao
import co.uk.gokul.letscook.core.database.entities.MealEntity
import co.uk.gokul.letscook.core.network.api.MealsService
import co.uk.gokul.letscook.feature.mealdetails.data.mapper.toDomain
import co.uk.gokul.letscook.feature.mealdetails.data.mapper.toEntity
import co.uk.gokul.letscook.feature.mealdetails.domain.model.MealDetails
import co.uk.gokul.letscook.feature.mealdetails.domain.repo.MealDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MealDetailsRepositoryImpl @Inject constructor(
    private val mealsService: MealsService,
    private val mealDao: MealDao,
    private val dbScope: CoroutineScope,
) : MealDetailsRepository {

    companion object {
        const val CACHE_TIMEOUT = 15 * 60 * 1000L
    }

    override suspend fun getMealsById(idMeal: String): Result<MealDetails> {
        val cachedMeal = mealDao.getMealsById(idMeal = idMeal)
        if (isCacheValid(cachedMeal)) {
            return Result.success(MealDetails(meals = cachedMeal.map { it.toDomain() }))
        }
        return try {
            val mealDetails = mealsService.getMealsById(idMeal = idMeal)
            dbScope.launch {
                mealDao.addMeals(mealDetails.meals.map { it.toEntity() })
            }
            Result.success(mealDetails.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun isCacheValid(cachedMeal: List<MealEntity>) = cachedMeal.isNotEmpty()
            && cachedMeal.first().strIngredient1?.isNotBlank() == true
            && cachedMeal.first().strIngredient2?.isNotBlank() == true
            && cachedMeal.first().strMeasure1?.isNotBlank() == true
            && cachedMeal.first().strMeasure2?.isNotBlank() == true
            && cachedMeal.first().strInstructions?.isNotBlank() == true
            && (System.currentTimeMillis() - cachedMeal.first().cachedAt < CACHE_TIMEOUT)
}