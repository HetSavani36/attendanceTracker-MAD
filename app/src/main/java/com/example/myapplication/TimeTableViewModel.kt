package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.DataClasses.Timetable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class TimeTableViewModel: ViewModel() {

    private val _timeTable= MutableStateFlow<Timetable>(Timetable())
    val timeTable: StateFlow<Timetable> = _timeTable

    private val _isTimeTableLoading= MutableStateFlow<Boolean>(false)
    val isTimeTableLoading: StateFlow<Boolean> = _isTimeTableLoading

    fun getTimeTable(className: String?){
        viewModelScope.launch {
            _isTimeTableLoading.value=true
            Log.d("Checking","Entered")
            try {
                val date = LocalDate.now().toString()  // "2025-07-27"
                val response= RetrofitClient.apiService.getTimeTable(className,date)
                _timeTable.value=response
                Log.d("Checking","Done")
            }catch (e: Exception){
                Log.d("Error",e.message.toString())
            } finally {
              _isTimeTableLoading.value=false
            }
        }
    }
    fun getTimeTable(className: String?,facultyId:String){
        viewModelScope.launch {
            _isTimeTableLoading.value=true
            Log.d("Checking","Entered")
            try {
                val date = LocalDate.now().toString()  // "2025-07-27"
                val response= RetrofitClient.apiService.getTimeTable(className,date,facultyId)
                _timeTable.value=response
                Log.d("Checking","Done")
            }catch (e: Exception){
                Log.d("Error",e.message.toString())
            } finally {
                _isTimeTableLoading.value=false
            }
        }
    }
    fun mark(_id:String,count: String){
        viewModelScope.launch {
            try {
                val date= LocalDate.now().toString()
                RetrofitClient.apiService.mark(_id,date,count)
            }catch (e: Exception){
                Log.d("Error",e.message.toString())
            }
        }
    }
//
}