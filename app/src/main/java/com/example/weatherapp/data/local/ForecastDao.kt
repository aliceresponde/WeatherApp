package com.example.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(data: List<ForecastEntity>)

    @Transaction
    suspend fun insertForecast(data: List<ForecastEntity>, cityName: String) {
        deleteCityForecast(cityName)
        insertForecast(data)
    }

    @Query("Select * from forecast_table where cityName = :name order by timeStamp DESC")
    suspend fun getCityForeCast(name: String): List<ForecastEntity>

    @Query("DELETE FROM forecast_table where cityName = :name")
    suspend fun deleteCityForecast(name: String)
}
