package com.example.myapplication.DataClasses

data class TimetableItem(
    val _id: String,
    val attendanceCount: Int,
    val facultyFullName: String,
    val facultyName: String,
    val location: String,
    val slot: Int,
    val subjectCode: String,
    val subjectName: String,
    val batch: String
)