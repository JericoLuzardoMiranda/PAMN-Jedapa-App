package com.example.jedapaappf1

interface ResultsRepository {
    suspend fun getDrivers(callback: (List<DriverResult>) -> Unit)
}