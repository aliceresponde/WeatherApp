package com.example.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert
    suspend fun insert(item: WeatherEntity)

    @Insert
    suspend fun insertAll(items: List<WeatherEntity>)

    @Query("Select *  from weatherentity")
    fun getAllPlacesFlow(): Flow<List<WeatherEntity>>
}
