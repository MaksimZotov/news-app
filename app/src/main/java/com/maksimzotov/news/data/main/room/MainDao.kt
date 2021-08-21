package com.maksimzotov.news.data.main.room

import androidx.room.*
import com.maksimzotov.news.data.main.room.domain.MainTableNames
import com.maksimzotov.news.domain.entities.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Query("SELECT * FROM ${MainTableNames.FAVORITES} ORDER BY url ASC")
    fun getFavoriteNews(): Flow<List<NewsItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNewsItemToFavorites(newsItem: NewsItem)

    @Delete
    fun removeNewsItemFromFavorites(newsItem: NewsItem)
}