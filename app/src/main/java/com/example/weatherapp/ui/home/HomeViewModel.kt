package com.example.weatherapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.PlaceItem
import com.example.weatherapp.domain.usecases.SaveMarkerUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val saveMarker: SaveMarkerUseCase
//    ,
//    private val deleteMarker: DeleteMarkerUseCase,
//    private val getMarkers: GetMarkersUseCase
) : ViewModel() {
    fun deletePlace(place: PlaceItem) {
        viewModelScope.launch(coroutineDispatcher) {
            saveMarker.invoke(latitude = place.lat, logitud = place.long)
        }
    }
}
