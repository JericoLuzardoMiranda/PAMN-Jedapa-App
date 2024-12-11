package com.example.jedapaappf1.data

interface HomeRepository {
    suspend fun getNews(callback: (List<News>) -> Unit)
    suspend fun getInterviews(callback: (List<News>) -> Unit)
    suspend fun getGames(callback: (List<News>) -> Unit)
    suspend fun getRaceSummaries(callback: (List<News>) -> Unit)
    suspend fun getTeamsDrivers(callback: (List<News>) -> Unit)
}