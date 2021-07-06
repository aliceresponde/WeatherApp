package com.example.weatherapp.domain

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.local.ForecastEntity
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.model.CityResponse
import com.example.weatherapp.data.model.CurrentWeatherResponse
import com.example.weatherapp.data.model.ForecastResponse
import com.example.weatherapp.data.model.ForecastWeatherResponse
import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.domain.model.ForecastItem
import com.example.weatherapp.ui.utils.currentSystemTime
import com.example.weatherapp.ui.utils.toFormatDateSting
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Calendar

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

// ====================== remote to Domain ===================
fun CurrentWeatherResponse.toCurrentWeatherItem(): CurrentWeatherItem {
    val weather = networkWeatherDescriptions.first()
    val main = networkWeatherCondition
    return CurrentWeatherItem(
        cityId = cityId,
        placeName = name,
        weatherName = weather.weatherName,
        weatherDesc = weather.weatherDescription,
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

fun CurrentWeatherResponse.toCurrentWeatherEntity(): WeatherEntity {
    val mWeather = this.networkWeatherDescriptions.first()
    val main = this.networkWeatherCondition

    return WeatherEntity(
        cityId = this.cityId,
        cityName = this.name,
        country = this.system.country,
        mainMinTemp = main.temp_min,
        mainMaxTemp = main.temp_max,
        mainTemp = main.temp,
        mainHumidity = main.humidity,
        mainFeel = main.feels_like,
        mainPressure = main.pressure,
        weatherDesc = mWeather.weatherDescription,
        weatherIcon = mWeather.weatherIcon,
        weatherName = mWeather.weatherName,
        windSpeed = this.wind.speed,
        date = currentSystemTime(),
        dt = this.dateUTC,
        timeStamp = Calendar.getInstance().timeInMillis
    )
}

fun WeatherEntity.toCurrentWeatherItem(): CurrentWeatherItem {
    return CurrentWeatherItem(
        cityId = cityId,
        placeName = cityName,
        currentTemp = "Temp: $mainTemp",
        minTemp = "Min: $mainMinTemp",
        maxTemp = "Max: $mainMaxTemp",
        feel = mainFeel.toString(),
        humidity = "Humidity $mainHumidity",
        pressure = "Pressure: $mainPressure",
        windSpeed = "Wind Speed:  $windSpeed",
        iconUrl = "${BuildConfig.IMG_PREFIX_URL}${weatherIcon}${BuildConfig.IMG_POSFIX_URL}",
        weatherName = weatherName,
        weatherDesc = weatherDesc,
        country = country,
        dt = dt,
        date = currentSystemTime()
    )
}

fun ForecastWeatherResponse.toForeCastWeatherItemList(): List<ForecastItem> {
    val city = city
    return weathers.map { item ->
        ForecastItem(
            cityName = city.name,
            country = city.country,
            weatherName = item.weather.first().weatherName,
            weatherDesc = item.weather.first().weatherDescription,
            iconUrl = "${BuildConfig.IMG_PREFIX_URL}${item.weather.first().weatherIcon}${BuildConfig.IMG_POSFIX_URL}",
            pressure = "Pressure: ${item.main.pressure}",
            humidity = "Humidity: ${item.main.humidity}",
            windSpeed = "Wind speed: ${item.wind.speed}",
            date = item.date,
            cityId = this.city.id,
            currentTemp = "Temp: ${item.main.temp}",
            minTemp = "Min: ${item.main.temp_min}",
            maxTemp = "Max: ${item.main.temp_max}",
            feel = item.main.feels_like.toString(),
            dt = item.dt
        )
    }
}

fun ForecastResponse.toForecastEntity(city: CityResponse): ForecastEntity {
    val mWeather = this.weather.first()
    return ForecastEntity(
        cityId = city.id,
        country = city.country,
        cityName = city.name,
        mainTemp = main.temp,
        mainMinTemp = this.main.temp_min,
        mainMaxTemp = main.temp_max,
        mainHumidity = main.humidity,
        mainPressure = main.pressure,
        mainFeel = main.feels_like,
        weatherName = mWeather.weatherName,
        weatherIcon = mWeather.weatherIcon,
        weatherDesc = mWeather.weatherDescription,
        windSpeed = wind.speed,
        date = date,
        dt = dt,
        timeStamp = Calendar.getInstance().timeInMillis
    )
}

fun ForecastWeatherResponse.toForecastEntityList(): List<ForecastEntity> {
    return this.weathers.map { it.toForecastEntity(this.city) }
}

fun ForecastWeatherResponse.toForecastEntity(): ForecastEntity {
    val weather = this.weathers.first()
    return ForecastEntity(
        cityId = this.city.id,
        cityName = this.city.name,
        country = this.city.country,
        mainTemp = weather.main.temp,
        mainMinTemp = weather.main.temp_min,
        mainMaxTemp = weather.main.temp_max,
        mainHumidity = weather.main.humidity,
        mainPressure = weather.main.pressure,
        mainFeel = weather.main.feels_like,
        windSpeed = weather.wind.speed,
        weatherName = weather.weather.first().weatherName,
        weatherDesc = weather.weather.first().weatherDescription,
        weatherIcon = weather.weather.first().weatherIcon,
        date = weather.date,
        dt = weather.dt,
        timeStamp = Calendar.getInstance().timeInMillis
    )
}

fun List<ForecastEntity>.toForecastItemList(): List<ForecastItem> {
    return this.map(ForecastEntity::toForecastItem)
}

fun ForecastEntity.toForecastItem(): ForecastItem {
    return ForecastItem(
        cityId = this.cityId,
        cityName = this.cityName,
        currentTemp = "Temp: $mainTemp",
        minTemp = "Min: $mainMinTemp",
        maxTemp = "Max: $mainMaxTemp",
        feel = mainFeel.toString(),
        date = date,
        humidity = "Humidity $mainHumidity",
        pressure = "Pressure: $mainPressure",
        windSpeed = "Wind Speed:  $windSpeed",
        iconUrl = "${BuildConfig.IMG_PREFIX_URL}${weatherIcon}${BuildConfig.IMG_POSFIX_URL}",
        weatherName = weatherName,
        weatherDesc = weatherDesc,
        country = country,
        dt = dt
    )
}