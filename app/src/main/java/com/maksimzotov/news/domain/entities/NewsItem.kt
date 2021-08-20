package com.maksimzotov.news.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsItem(
    val title: String,
    val urlToImage: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
