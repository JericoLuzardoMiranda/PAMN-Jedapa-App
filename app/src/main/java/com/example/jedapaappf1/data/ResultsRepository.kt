package com.example.jedapaappf1.data

interface ResultsRepository {
    suspend fun getDrivers(callback: (List<DriverResult>) -> Unit)
    suspend fun getTeams(callback: (List<TeamResult>) -> Unit)
}