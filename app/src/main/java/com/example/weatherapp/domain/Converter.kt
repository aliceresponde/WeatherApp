package com.example.weatherapp.domain

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.data.model.NetworkWeatherForecastResponse
import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.domain.model.ForecastWeatherItem
import com.example.weatherapp.domain.model.PlaceItem
import com.example.weatherapp.ui.utils.currentSystemTime
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

fun roundOffDecimal(number: Double): Double {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(number).toDouble()
}

fun round(value: Double, places: Int = 3): Double {
    require(places >= 0)
    var bd = BigDecimal(value.toString())
    bd = bd.setScale(places, RoundingMode.HALF_UP)
    return bd.toDouble()
}

fun PlaceItem.toMarkerOption(): MarkerOptions {
    return MarkerOptions().draggable(false).position(LatLng(lat, long)).title(name)
}

fun CurrentWeather.toCurrentWeatherItem(): CurrentWeatherItem {
    val weather = networkWeatherDescriptions.first()
    val main = networkWeatherCondition
    return CurrentWeatherItem(
        cityId = cityId,
        placeName = name,
        weatherName = weather.weatherName ?: "",
        weatherDesc = weather.weatherDescription ?: "",
        iconUrl = "${BuildConfig.IMG_PREFIX_URL}${weather.weatherIcon}${BuildConfig.IMG_POSFIX_URL}",
        currentTemp = "Temp: ${main.temp}",
        minTemp = "Min: ${main.temp_min}",
        humidity = "Humidity: ${main.humidity}",
        pressure = "Pressure: ${main.pressure}",
        windSpeed = "Wind Speed: ${wind.speed}",
        date = currentSystemTime(),
        dt = this.dateUTC,
        country = this.system.country,
        maxTemp = "Max: ${main.temp_max}",
        feel = main.feels_like.toString()
    )
}

fun NetworkWeatherForecastResponse.toForeCastWeatherItemList(): List<ForecastWeatherItem> {
    val city = city
    return weathers.map { weatherCasts ->
        ForecastWeatherItem(
            placeName = city.name,
            weatherName = weatherCasts.weather.first().weatherName ?: "",
            weatherDesc = weatherCasts.weather.first().weatherDescription ?: "",
            iconUrl = "${BuildConfig.IMG_PREFIX_URL}${weatherCasts.weather.first().weatherIcon}${BuildConfig.IMG_POSFIX_URL}",
            temp = "Temp: ${weatherCasts.main.temp}",
            pressure = "Pressure: ${weatherCasts.main.pressure}",
            humidity = "Humidity: ${weatherCasts.main.humidity}",
            windSpeed = "Wind speed: ${weatherCasts.wind.speed}",
            date = weatherCasts.date,
            country = city.country,
            cityId = this.city.id,
            dt = weatherCasts.dt
        )
    }
}