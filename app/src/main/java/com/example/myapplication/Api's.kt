package com.example.myapplication

import com.example.myapplication.DataClasses.TimeTable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.sql.Date

interface ApiService{

    @GET("api/timetable/{className}")
    suspend fun getTimeTable(@Path("className") className:String,@Query("date") date: String): TimeTable

//    @POST("api/attendance/mark")
//    suspend fun mark(@Body mark: Mark)
}