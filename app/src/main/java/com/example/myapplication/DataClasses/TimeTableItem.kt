package com.example.myapplication.DataClasses

import com.google.gson.JsonElement

data class TimeTableItem(
    val __v: Int,
    val _id: String,
    val attendanceCount: AttendanceCount,
    val `class`: String?=null,
    val day: String?=null,
    val lab: Lab?=null,
    val lecture: Lecture?=null,
    val slot: Int,
    val order: Int
)