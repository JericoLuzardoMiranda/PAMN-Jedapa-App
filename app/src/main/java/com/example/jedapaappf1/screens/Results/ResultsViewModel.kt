package com.example.jedapaappf1.screens.Results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jedapaappf1.data.FirebaseResultsRepository
import com.example.jedapaappf1.data.Result
import com.example.jedapaappf1.data.ResultsRepository

class ResultsViewModel : ViewModel(){
    val resultsRepository: ResultsRepository = FirebaseResultsRepository()
    private val _driversState = MutableLiveData<Result>()
    private val _teamsState = MutableLiveData<Result>()
    val driverState: LiveData<Result> = _driversState
    val teamState: LiveData<Result> = _teamsState
    suspend fun getDrivers(){
        resultsRepository.getDrivers { drivers ->
            _driversState.value = Result.DriversResultsSuccess(drivers)
        }
    }
    suspend fun getTeams(){
        resultsRepository.getTeams { teams ->
            _teamsState.value = Result.TeamsResultsSuccess(teams)
        }
    }
}