package com.example.jedapaappf1.screens.TypeNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jedapaappf1.data.FirebaseHomeRepository
import com.example.jedapaappf1.data.HomeRepository
import com.example.jedapaappf1.data.Result

class TypeNewsViewModel : ViewModel() {
    val homeRepository: HomeRepository = FirebaseHomeRepository()
    private val _interviewsState = MutableLiveData<Result>()
    private val _gamesState = MutableLiveData<Result>()
    private val _raceSummariesState = MutableLiveData<Result>()
    private val _teamsDriversState = MutableLiveData<Result>()
    val interviewsState: LiveData<Result> = _interviewsState
    val gamesState: LiveData<Result> = _gamesState
    val raceSummariesState: LiveData<Result> = _raceSummariesState
    val teamsDriversState: LiveData<Result> = _teamsDriversState
    suspend fun getInterviews(){
        homeRepository.getInterviews { interviews ->
            _interviewsState.value = Result.HomeSuccess(interviews)
        }
    }
    suspend fun getGames(){
        homeRepository.getGames { games ->
            _gamesState.value = Result.HomeSuccess(games)
        }
    }
    suspend fun getRaceSummaries(){
        homeRepository.getRaceSummaries { raceSummaries ->
            _raceSummariesState.value = Result.HomeSuccess(raceSummaries)
        }
    }
    suspend fun getTeamsDrivers(){
        homeRepository.getTeamsDrivers { teamsDrivers ->
            _teamsDriversState.value = Result.HomeSuccess(teamsDrivers)
        }
    }
}