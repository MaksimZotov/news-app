package com.maksimzotov.news.di.data

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.maksimzotov.news.data.main.RepositoryImpl
import com.maksimzotov.news.data.main.retrofit.NewsApi
import com.maksimzotov.news.data.main.retrofit.RetrofitConstants
import com.maksimzotov.news.data.main.retrofit.deserializers.NewsDeserializer
import com.maksimzotov.news.data.main.room.MainDao
import com.maksimzotov.news.data.main.room.MainDatabase
import com.maksimzotov.news.data.main.room.RoomConstants
import com.maksimzotov.news.di.ApplicationMain
import com.maksimzotov.news.domain.Repository
import com.maksimzotov.news.domain.entities.NewsItem
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationMain::class)
abstract class MainModule {

    @Binds
    abstract fun bindRepositoryImplToRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

    @Provides
    @Singleton
    fun provideMainDao(
        mainDatabase: MainDatabase
    ): MainDao {
        return mainDatabase.mainDao()
    }

    @Provides
    @Singleton
    fun provideMainDatabase(context: Context): MainDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MainDatabase::class.java,
            RoomConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(
        retrofit: Retrofit
    ): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        newsDeserializer: NewsDeserializer
    ): Retrofit {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(NewsItem::class.java, newsDeserializer)
        val myGson = gsonBuilder.create()
        val converterFactory = GsonConverterFactory.create(myGson)

        return Retrofit.Builder()
            .baseUrl(RetrofitConstants.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }
}