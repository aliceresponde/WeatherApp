package com.example.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.weatherapp.data.model.CurrentWeather

@Database(entities = [Place::class, WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun placeDao(): PlacesDao
    abstract fun weatherDao(): WeatherDao
}