package com.example.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlacesDao {
    @Insert
    suspend fun insert(place: Place)

    @Insert
    suspend fun insertAll(places: List<Place>)

    @Query("Select *  from place")
    fun getAllPlaces(): Flow<List<Place>>


    @Query("SELECT * FROM Place WHERE lat = :latitude & lng =:longitude LIMIT 1")
    suspend fun getPlaceBy(latitude: Long, longitude: Long): Place

    @Query("SELECT * FROM Place WHERE id = :id LIMIT 1")
    suspend fun getPlaceBy(id: Int): Place

    @Query("SELECT * FROM Place WHERE name LIKE '%' || :name || '%'")
    fun getPlacesByNameFlow(name: String): Flow<List<Place>>


    @Delete
    suspend fun delete(place: Place)

    @Delete
    suspend fun deleteAll(place: Place)

}
