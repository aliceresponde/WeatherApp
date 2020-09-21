package com.example.weatherapp.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CitiesFragmentBinding
import com.example.weatherapp.domain.model.PlaceItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : BaseFragment<CitiesFragmentBinding>() {

    private val viewModel: CitiesViewModel by viewModels()
    private val adapter: WeatherForecastAdapter by lazy { WeatherForecastAdapter() }
    private val args: CitiesFragmentArgs by navArgs()
    private val place: PlaceItem by lazy { args.placeItem }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CitiesFragmentBinding.inflate(inflater)
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        binding.viewModel = viewModel
        viewModel.currentWeather.observe(viewLifecycleOwner, {
            binding.currentW = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weatherList.adapter = adapter
        binding.todayWeatherCard.visibility = GONE
        viewModel.getCurrentWeatherBy(place.lat, place.long)
    }
}