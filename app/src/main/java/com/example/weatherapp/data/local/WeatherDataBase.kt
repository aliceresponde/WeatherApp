package com.example.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Place::class, WeatherEntity::class, ForecastEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun placeDao(): PlacesDao
    abstract fun weatherDao(): WeatherDao
    abstract fun forecastDao(): ForecastDao
}