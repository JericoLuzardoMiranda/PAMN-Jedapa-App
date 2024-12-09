package com.example.jedapaappf1.data

sealed class Result {
    data class DriversResultsSuccess(val items: List<DriverResult>): Result()
    data class TeamsResultsSuccess(val items: List<TeamResult>): Result()
    data class DriversSuccess(val items: List<Driver>): Result()
    data class TeamsSuccess(val items: List<Team>): Result()
    data class CalendarSuccess(val items: List<Circuit>): Result()
    data class Error(val exception: Exception): Result()
}