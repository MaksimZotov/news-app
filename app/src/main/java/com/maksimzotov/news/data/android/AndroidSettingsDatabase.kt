package com.maksimzotov.news.data.android

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimzotov.news.presentation.entities.DarkTheme

@Database(
    entities = [
        DarkTheme::class,
    ],
    version = 1
)
abstract class AndroidSettingsDatabase : RoomDatabase() {
    abstract fun androidSettingsDao(): AndroidSettingsDao
}