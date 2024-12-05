package com.example.jedapaappf1.data

sealed class Result {
    data class DriversResultsSuccess(val items: List<DriverResult>): Result()
    data class TeamsResultsSuccess(val items: List<TeamResult>): Result()
    data class Error(val exception: Exception): Result()
}