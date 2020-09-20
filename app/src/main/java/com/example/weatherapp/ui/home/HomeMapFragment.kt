package com.example.weatherapp.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHomeMapBinding
import com.example.weatherapp.domain.model.PlaceItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

private val Marker.toPlaceItem: PlaceItem
    get() {
        return PlaceItem(
            lat = this.position.latitude,
            long = this.position.longitude,
            name = this.title
        )
    }

@AndroidEntryPoint
class HomeMapFragment : BaseFragment<FragmentHomeMapBinding>(), OnMapReadyCallback {
    private val viewModel: HomeViewModel by viewModels()
    private var googleMap: GoogleMap? = null
    private val placeList = mutableListOf<PlaceItem>()
    private var markerPlaceMap: MutableMap<Marker, PlaceItem> = mutableMapOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeMapBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = binding.map as GoogleMap
        googleMap?.apply {
            uiSettings.isMyLocationButtonEnabled = true
            setOnMapClickListener { latLng ->
                val marker = addMarker(MarkerOptions().draggable(false).position(latLng))
                val element = marker.toPlaceItem
                placeList.add(element)
                markerPlaceMap[marker] = element
            }

            setOnMarkerClickListener { marker ->
                showRemoveMarkerFromMapDialog(marker)
                false
            }
        }
    }

    private fun showRemoveMarkerFromMapDialog(marker: Marker) {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.remove_marker_question)
                .setPositiveButton(R.string.remove,
                    DialogInterface.OnClickListener { _, _ ->
                        marker.remove()
                        val place = markerPlaceMap[marker]
                        markerPlaceMap.remove(marker)
                        viewModel.deletePlace(place)

                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
            builder.show()
        }
    }
}