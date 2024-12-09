package com.example.jedapaappf1.screens.Calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jedapaappf1.data.CalendarRepository
import com.example.jedapaappf1.data.FirebaseCalendarRepository
import com.example.jedapaappf1.data.Result

class CalendarViewModel : ViewModel() {
    val calendarRepository: CalendarRepository = FirebaseCalendarRepository()
    private val _calendarState = MutableLiveData<Result>()
    val calendarState: LiveData<Result> = _calendarState
    suspend fun getCircuits(){
        calendarRepository.getCircuits { circuits ->
            _calendarState.value = Result.CalendarSuccess(circuits)
        }
    }
}