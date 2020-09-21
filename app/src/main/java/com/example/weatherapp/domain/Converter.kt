package com.example.weatherapp.domain

import com.example.weatherapp.domain.model.PlaceItem
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.math.RoundingMode
import java.text.DecimalFormat

fun roundOffDecimal(number: Double): Double {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(number).toDouble()
}

fun PlaceItem.toMarkerOption(): MarkerOptions {
    return MarkerOptions().draggable(false).position(LatLng(lat, long)).title(name)
}