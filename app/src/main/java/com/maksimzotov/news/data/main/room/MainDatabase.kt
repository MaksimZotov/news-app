package com.maksimzotov.news.data.main.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimzotov.news.domain.entities.NewsItem

@Database(
    entities = [
        NewsItem::class
    ],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}