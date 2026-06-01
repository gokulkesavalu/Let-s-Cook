package co.uk.gokul.letscook.feature.mealdetails.di

import co.uk.gokul.letscook.feature.mealdetails.data.repo.MealDetailsRepositoryImpl
import co.uk.gokul.letscook.feature.mealdetails.domain.repo.MealDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MealDetailsModule {

    @Binds
    @Singleton
    abstract fun bindsMealDetailsRepo(mealDetailsRepositoryImpl: MealDetailsRepositoryImpl): MealDetailsRepository
}