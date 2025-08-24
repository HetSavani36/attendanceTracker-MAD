package com.example.myapplication

import com.example.myapplication.DataClasses.Timetable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

//nothing
interface ApiService{

    @GET("api/timetable/{className}")
    suspend fun getTimeTable(@Path("className") className: String?, @Query("date") date: String): Timetable

    @GET("api/timetable/{className}")
    suspend fun getTimeTable(@Path("className") className: String?, @Query("date") date: String, @Query("date") facultyId: String): Timetable

    @POST("api/attendance/mark/{id}")
    suspend fun mark(@Path("id") id: String,@Query("date") date: String, @Query("count") count: String)

//    @POST("api/attendance/mark/{id}/{batch}")
//    suspend fun mark(@Path("id") id: String,@Path("batch") batch: String, @Query("date") date: String, @Body count: Count)
}
