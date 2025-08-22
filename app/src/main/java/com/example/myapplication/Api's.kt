package com.example.myapplication

import com.example.myapplication.DataClasses.Count
import com.example.myapplication.DataClasses.TimeTable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

//nothing
interface ApiService{

    @GET("api/timetable/{className}")
    suspend fun getTimeTable(@Path("className") className: String?, @Query("date") date: String): TimeTable

    @POST("api/attendance/mark/{id}")
    suspend fun mark(@Path("id") id: String,@Query("date") date: String, @Body count: Count)

    @POST("api/attendance/mark/{id}/{batch}")
//    @POST("api/attendance/mark/{className}/{slot}")
//    suspend fun mark(@Path("className") className: String?, @Path("slot") slot: Int, @Query("date") date: String, @Body count: Count)
    suspend fun mark(@Path("id") id: String,@Path("batch") batch: String, @Query("date") date: String, @Body count: Count)
}
