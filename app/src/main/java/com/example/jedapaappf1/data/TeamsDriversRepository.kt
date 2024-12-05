package com.example.jedapaappf1.data

interface TeamsDriversRepository {
    suspend fun getDrivers(callback: (List<Driver>) -> Unit)
    suspend fun getTeams(callback: (List<Team>) -> Unit)
}