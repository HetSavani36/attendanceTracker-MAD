package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.DataClasses.TimeTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

class TimeTableViewModel: ViewModel() {

    private val _timeTable= MutableStateFlow<TimeTable>(TimeTable())
    val timeTable: StateFlow<TimeTable> = _timeTable

    private val _isTimeTableLoading= MutableStateFlow<Boolean>(false)
    val isTimeTableLoading: StateFlow<Boolean> = _isTimeTableLoading

    fun getTimeTable(className: String){
        viewModelScope.launch {
            _isTimeTableLoading.value=true
            Log.d("Checking","Entered")
            try {
//                val formatted = LocalDate.now().toString()  // "2025-07-27"
//                Log.d("Checking",formatted)

                Log.d("Checking","Entered")
                val response= RetrofitClient.apiService.getTimeTable(className,"2025-07-21")
                _timeTable.value=response
                Log.d("Checking","Done")
            }catch (e: Exception){
                Log.d("Error",e.message.toString())
            } finally {
              _isTimeTableLoading.value=false
            }
        }
    }

//    fun mark(mark: Mark){
//        viewModelScope.launch {
//            try {
//                RetrofitClient.apiService.mark(mark)
//            }catch (e: Exception){
//                Log.d("Error",e.message.toString())
//            }finally {
//                val day = LocalDate.now().dayOfWeek.name
//                getTimeTable(mark.classs,day)
//            }
//        }
//    }

}