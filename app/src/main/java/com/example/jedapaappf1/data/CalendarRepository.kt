package com.example.jedapaappf1.data

interface CalendarRepository {
    suspend fun getCircuits(callback: (List<Circuit>) -> Unit)
}