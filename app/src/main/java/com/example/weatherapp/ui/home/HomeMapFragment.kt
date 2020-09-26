package com.example.weatherapp.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHomeMapBinding
import com.example.weatherapp.domain.model.PlaceItem
import com.example.weatherapp.domain.round
import com.example.weatherapp.domain.roundOffDecimal
import com.example.weatherapp.domain.toMarkerOption
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


private val Marker.toPlaceItem: PlaceItem
    get() {
        return PlaceItem(
            lat = round(position.latitude),
            long = round(position.longitude),
            name = title
        )
    }

@AndroidEntryPoint
class HomeMapFragment : BaseFragment<FragmentHomeMapBinding>(), OnMapReadyCallback {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var googleMap: GoogleMap
    private val adapter: PlaceItemAdapter by lazy { PlaceItemAdapter(onItemClickListener = ::goToCitiesScreen) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeMapBinding.inflate(inflater)
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.getAllPlaces().observe(viewLifecycleOwner) {
            loadPlacesInMap(it)
        }
    }

    private fun loadPlacesInMap(places: List<PlaceItem>) {
        val markers = places.map { it.toMarkerOption() }
        googleMap.clear()
        markers.forEach { googleMap.addMarker(it) }
        adapter.updateData(places)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        binding.markerList.adapter = adapter
        binding.markerList.addItemDecoration( dividerItemDecoration)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.apply {
            uiSettings.isMyLocationButtonEnabled = true
            setOnMapClickListener { latLng ->
                val marker =
                    addMarker(MarkerOptions().draggable(false).position(latLng).title("marker"))
                val place = marker.toPlaceItem
                viewModel.save(place)
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
                        viewModel.deletePlace(marker.position)
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

    fun goToCitiesScreen(placeItem: PlaceItem) {
        val action = HomeMapFragmentDirections.actionHomeMapFragmentToCitiesFragment(placeItem)
        findNavController().navigate(action)
    }
}


