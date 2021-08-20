package com.maksimzotov.news.di.data.main

import com.maksimzotov.news.data.main.RepositoryImpl
import com.maksimzotov.news.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainBindModule {

    @Binds
    fun bindRepositoryImplToRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}