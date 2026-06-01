package co.uk.gokul.letscook.feature.mealdetails.domain.repo

import co.uk.gokul.letscook.feature.mealdetails.domain.model.MealDetails

interface MealDetailsRepository {

    suspend fun getMealsById(idMeal: String): Result<MealDetails>

}