package com.example.weatherapp.data.local

import android.util.Log
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlacesDao {
    @Insert
    suspend fun insert(place: Place)

    @Insert
    suspend fun insertAll(places: List<Place>)

    @Query("Select *  from place")
    fun getAllPlacesFlow(): Flow<List<Place>>


    @Query("SELECT * FROM Place WHERE lat = :latitude AND lng =:longitude LIMIT 1")
    suspend fun getPlaceBy(latitude: Double, longitude: Double): Place

    @Query("SELECT * FROM Place WHERE id = :id LIMIT 1")
    suspend fun getPlaceBy(id: Int): Place

    @Query("SELECT * FROM Place WHERE name LIKE '%' || :name || '%'")
    fun getPlacesByNameFlow(name: String): Flow<List<Place>>


    @Delete
    suspend fun delete(place: Place)

    @Delete
    suspend fun deleteAll(place: Place)

    //    @Query("Delete  FROM place WHERE lat = :latitude & lng =:longitude")
    @Transaction
    suspend fun deletePlaceWithLatLong(latitude: Double, longitude: Double) {
       try {

        val place = getPlaceBy(latitude, longitude)
       delete(place)
       }catch (t: Throwable){
           Log.e("delete ER", t.message ?: "no message")
       }
    }

}
