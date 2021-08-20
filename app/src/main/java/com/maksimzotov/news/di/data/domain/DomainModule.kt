package com.maksimzotov.news.di.data.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideCoroutineDispatcherForIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @ForHeavyCalculations
    fun provideCoroutineDispatcherForHeavyCalculations(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForHeavyCalculations