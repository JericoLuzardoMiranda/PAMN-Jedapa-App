package com.example.jedapaappf1.screens.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jedapaappf1.data.FirebaseHomeRepository
import com.example.jedapaappf1.data.HomeRepository
import com.example.jedapaappf1.data.Result

class HomeViewModel : ViewModel() {
    val homeRepository: HomeRepository = FirebaseHomeRepository()
    private val _homeState = MutableLiveData<Result>()
    val homeState: LiveData<Result> = _homeState
    suspend fun getNews(){
        homeRepository.getNews { news ->
            _homeState.value = Result.HomeSuccess(news)
        }
    }
}