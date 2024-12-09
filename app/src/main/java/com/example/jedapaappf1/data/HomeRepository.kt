package com.example.jedapaappf1.data

interface HomeRepository {
    suspend fun getNews(callback: (List<News>) -> Unit)
}