package com.maksimzotov.news.di.data.android

import android.content.Context
import androidx.room.Room
import com.maksimzotov.news.data.android.AndroidSettingsConstants
import com.maksimzotov.news.data.android.AndroidSettingsDao
import com.maksimzotov.news.data.android.AndroidSettingsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AndroidModule {

    @Provides
    @Singleton
    fun provideAndroidSettingsDao(
        androidSettingsDatabase: AndroidSettingsDatabase
    ): AndroidSettingsDao {
        return androidSettingsDatabase.androidSettingsDao()
    }

    @Provides
    @Singleton
    fun provideAndroidSettingsDatabase(context: Context): AndroidSettingsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AndroidSettingsDatabase::class.java,
            AndroidSettingsConstants.DATABASE_NAME
        ).build()
    }
}