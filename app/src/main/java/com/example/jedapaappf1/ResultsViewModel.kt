package com.example.jedapaappf1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultsViewModel : ViewModel(){
    val resultsRepository: ResultsRepository = FirebaseResultsRepository()
    private val _driversState = MutableLiveData<Result>()
    val driverState: LiveData<Result> = _driversState
    suspend fun getDrivers(){
        resultsRepository.getDrivers { drivers ->
            _driversState.value = Result.DriversResultsSuccess(drivers)
        }
    }
}