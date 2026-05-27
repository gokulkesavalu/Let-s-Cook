package co.uk.gokul.letscook.feature.home.di

import co.uk.gokul.letscook.feature.home.data.repo.HomeRepositoryImpl
import co.uk.gokul.letscook.feature.home.domain.repo.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Dagger module for home-related dependencies.
 * This module is installed in [SingletonComponent].
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    /**
     * Binds the [HomeRepositoryImpl] to [HomeRepository].
     */
    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    companion object {
        /**
         * Provides a [CoroutineScope] with [Dispatchers.Default] and a [SupervisorJob].
         */
        @Provides
        @Singleton
        fun provideCoroutineScope() = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }
}