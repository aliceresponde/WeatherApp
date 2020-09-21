package com.example.weatherapp.domain

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.domain.model.PlaceItem
import com.example.weatherapp.ui.utils.currentSystemTime
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

fun CurrentWeather.toCurrentWeatherItem(): CurrentWeatherItem {
    val weather = networkWeatherDescriptions.first()
    val main = networkWeatherCondition
    return CurrentWeatherItem(
        id = cityId,
        placeName = name,
        weatherName = weather.main ?: "",
        weatherDesc = weather.description ?: "",
        iconUrl = "${BuildConfig.IMG_PREFIX_URL}${weather.icon}${BuildConfig.IMG_POSFIX_URL}",
        temp = "Temp: ${main.temp}",
        humidity = "Humedity: ${main.humidity}",
        pressure = "Pressure: ${main.pressure}",
        windSpeed = "Wind Speed: ${wind.speed}",
        date = currentSystemTime()
    )
}