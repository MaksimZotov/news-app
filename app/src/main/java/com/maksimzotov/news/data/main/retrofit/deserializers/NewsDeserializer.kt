package com.maksimzotov.news.data.main.retrofit.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.maksimzotov.news.domain.entities.NewsItem
import java.lang.reflect.Type
import javax.inject.Inject

class NewsDeserializer @Inject constructor() : JsonDeserializer<List<NewsItem>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<NewsItem> {
        TODO("Not yet implemented")
    }
}