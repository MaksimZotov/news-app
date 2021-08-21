package com.maksimzotov.news.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maksimzotov.news.data.main.room.domain.MainTableNames

@Entity(tableName = MainTableNames.FAVORITES)
data class NewsItem(
    val title: String,
    val url: String,
    val urlToImage: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
