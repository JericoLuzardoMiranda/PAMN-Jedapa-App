package com.example.jedapaappf1.screens.TeamsDrivers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jedapaappf1.data.FirebaseTeamsDriversRepository
import com.example.jedapaappf1.data.Result
import com.example.jedapaappf1.data.TeamsDriversRepository

class TeamsDriversViewModel : ViewModel() {
    val teamsDriversRepository: TeamsDriversRepository = FirebaseTeamsDriversRepository()
    private val _driversState = MutableLiveData<Result>()
    private val _teamsState = MutableLiveData<Result>()
    val driverState: LiveData<Result> = _driversState
    val teamState: LiveData<Result> = _teamsState
    suspend fun getDrivers(){
        teamsDriversRepository.getDrivers { drivers ->
            _driversState.value = Result.DriversSuccess(drivers)
        }
    }
    suspend fun getTeams(){
        teamsDriversRepository.getTeams { teams ->
            _teamsState.value = Result.TeamsSuccess(teams)
        }
    }
}