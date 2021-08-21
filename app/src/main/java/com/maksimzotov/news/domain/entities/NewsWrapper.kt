package com.maksimzotov.news.domain.entities

// This wrapper is implemented to get a list of news via retrofit.
// Using of a custom deserializer for a generic type (in our case, List<NewsItem>) was failed.
// Passing the generic type
// val type = object : TypeToken<List<NewsItem>> () {}.type
// to registerTypeAdapter(type, ...) in GsonBuilder did not help - the application was crashed.
// So I had to add the wrapper

data class NewsWrapper(
    val news: List<NewsItem>
)