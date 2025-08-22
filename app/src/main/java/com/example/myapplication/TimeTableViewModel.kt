package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.DataClasses.Count
import com.example.myapplication.DataClasses.TimeTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class TimeTableViewModel: ViewModel() {

    private val _timeTable= MutableStateFlow<TimeTable>(TimeTable())
    val timeTable: StateFlow<TimeTable> = _timeTable

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
    fun mark(className: String?,_id:String,count: Count){
        viewModelScope.launch {
            try {
                val date= LocalDate.now().toString()
                RetrofitClient.apiService.mark(_id,date,count)
            }catch (e: Exception){
                Log.d("Error",e.message.toString())
            }finally {
                val day = LocalDate.now().dayOfWeek.name
                getTimeTable(className)
            }
        }
    }

    fun mark(className: String?,_id:String,count: Count,batch: String){
        viewModelScope.launch {
            try {
                val date= LocalDate.now().toString()
                RetrofitClient.apiService.mark(_id,batch,date,count)
            }catch (e: Exception){
                Log.d("Error",e.message.toString())
            }finally {
                val day = LocalDate.now().dayOfWeek.name
                getTimeTable(className)
            }
        }
    }

}