package com.example.weatherapp.ui.cities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CitiesFragmentBinding
import com.example.weatherapp.domain.model.PlaceItem
import com.example.weatherapp.ui.utils.showIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : BaseFragment<CitiesFragmentBinding>() {

    private val viewModel: CitiesViewModel by viewModels()
    private val adapter: WeatherForecastAdapter by lazy { WeatherForecastAdapter() }
    private val args: CitiesFragmentArgs by navArgs()
    private val place: PlaceItem? by lazy { args.placeItem }

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

        viewModel.showCurrentWeatherCard.observe(viewLifecycleOwner, { state ->
            binding.apply {
                todayWeatherCard.showIf { state }
            }
        })

        viewModel.currentWeather.observe(viewLifecycleOwner, { w ->
            binding.currentW = w
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weatherList.adapter = adapter
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getCurrentWeatherByLocationName(query ?: "")
                val imm: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view!!.windowToken, 0)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        place?.run {
            viewModel.getCurrentWeatherBy(lat, long)
        }
    }
}