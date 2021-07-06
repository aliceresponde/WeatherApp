package com.example.weatherapp.ui.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun currentSystemTime(): String {
    val currentTime = System.currentTimeMillis()
    val date = Date(currentTime)
    val dateFormat = SimpleDateFormat("EEEE d MMM yyyy, hh:mm aaa")
    return dateFormat.format(date)
}

@SuppressLint("SimpleDateFormat")
fun Long.toFormatDateSting(dateFormat: String = "EEEE d MMM yyyy, hh:mm aaa"): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    return formatter.format(date)
}