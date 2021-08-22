package com.maksimzotov.news.data.main.retrofit.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.maksimzotov.news.domain.entities.NewsItem
import com.maksimzotov.news.domain.entities.NewsWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class NewsDeserializer @Inject constructor() : JsonDeserializer<NewsWrapper> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): NewsWrapper {

        json
            ?: throw IllegalArgumentException("json must not be null")

        val articles = json
            .asJsonObject
            .get("articles")
            .asJsonArray

        val news = mutableListOf<NewsItem>()

        articles.forEach { article ->
            val jsonObject = article.asJsonObject
            val title = jsonObject.get("title").toString().trim('\"')
                .replace("\\\"", "\"")
            val url = jsonObject.get("url").toString().trim('\"')
            val urlToImage = jsonObject.get("urlToImage").toString().trim('\"')
            news.add(NewsItem(title, url, urlToImage))
        }

        return NewsWrapper(news)
    }
}