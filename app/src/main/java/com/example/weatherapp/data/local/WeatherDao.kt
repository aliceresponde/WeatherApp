package com.example.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCurrentWeather(item: WeatherEntity)

    @Query("Select * from current_weather_table where cityName = :name order by timeStamp DESC  LIMIT 1 ")
    suspend fun getCurrentWeatherByCity(name: String): WeatherEntity

    @Query("DELETE FROM current_weather_table where cityName = :name")
    suspend fun deleteWeatherByCity(name: String)
}
