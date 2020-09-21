package com.example.weatherapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.PlaceItem
import com.example.weatherapp.domain.usecases.DeleteMarkerUseCase
import com.example.weatherapp.domain.usecases.GetMarkersUseCase
import com.example.weatherapp.domain.usecases.SaveMarkerUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val saveMarker: SaveMarkerUseCase,
    private val deleteMarker: DeleteMarkerUseCase,
    private val getMarkers: GetMarkersUseCase
) : ViewModel() {

    fun deletePlace(place: LatLng) {
        viewModelScope.launch(coroutineDispatcher) {
            deleteMarker.invoke(place)
        }
    }

    fun save(place: PlaceItem) {
        viewModelScope.launch(coroutineDispatcher) {
            saveMarker.invoke(latitude = place.lat, logitud = place.long)
        }
    }

    fun getAllPlaces(): LiveData<List<PlaceItem>> = getMarkers.invoke().asLiveData()
}
