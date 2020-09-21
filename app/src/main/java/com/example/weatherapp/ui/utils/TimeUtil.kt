package com.example.weatherapp.ui.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun currentSystemTime(): String {
    val currentTime = System.currentTimeMillis()
    val date = Date(currentTime)
    val dateFormat = SimpleDateFormat("EEEE MMM d, hh:mm aaa")
    return dateFormat.format(date)
}