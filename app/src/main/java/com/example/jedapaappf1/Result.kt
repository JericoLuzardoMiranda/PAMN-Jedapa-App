package com.example.jedapaappf1

sealed class Result {
    data class DriversResultsSuccess(val items: List<DriverResult>): Result()
    data class Error(val exception: Exception): Result()
}