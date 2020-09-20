package com.example.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Place::class], version = 1, exportSchema = false)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun placeDao(): PlacesDao
}