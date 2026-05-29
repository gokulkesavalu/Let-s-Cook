package co.uk.gokul.letscook.feature.meals.di

import co.uk.gokul.letscook.feature.meals.data.repo.MealsRepositoryImpl
import co.uk.gokul.letscook.feature.meals.domain.repo.MealsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for home-related dependencies.
 * This module is installed in [SingletonComponent].
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MealsModule {

    /**
     * Binds the [MealsRepositoryImpl] to [MealsRepository].
     */
    @Binds
    @Singleton
    abstract fun bindMealsRepository(mealsRepositoryImpl: MealsRepositoryImpl): MealsRepository

}