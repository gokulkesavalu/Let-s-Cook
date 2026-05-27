package co.uk.gokul.letscook.feature.home.data.repo

import co.uk.gokul.letscook.core.network.api.MealsService
import co.uk.gokul.letscook.feature.home.domain.model.Areas
import co.uk.gokul.letscook.feature.home.domain.model.Categories
import co.uk.gokul.letscook.feature.home.domain.model.Ingredients
import co.uk.gokul.letscook.feature.home.domain.repo.HomeRepository
import javax.inject.Inject

/**
 * Implementation of [HomeRepository] that fetches data from [MealsService].
 *
 * @property mealsService The API service used to fetch meal data.
 */
class HomeRepositoryImpl @Inject constructor(
    private val mealsService: MealsService,
) : HomeRepository {
    
    override suspend fun getCategories(): Categories {
        TODO("Not yet implemented")
    }

    override suspend fun getAreas(): Areas {
        TODO("Not yet implemented")
    }

    override suspend fun getIngredients(): Ingredients {
        TODO("Not yet implemented")
    }
}