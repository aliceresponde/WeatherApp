package com.example.weatherapp.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.databinding.CitiesFragmentBinding
import com.example.weatherapp.ui.utils.showIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : BaseFragment<CitiesFragmentBinding>() {

    private lateinit var adapter: WeatherForecastAdapter
    private val viewModel: CitiesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CitiesFragmentBinding.inflate(inflater)
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        binding.viewModel = viewModel

        with(viewModel) {

            currentCapital.observe(viewLifecycleOwner) {
                viewModel.getForecastWeatherByLocationName(it)
            }

            showForecastRecycler.observe(viewLifecycleOwner, { state ->
                binding.apply { forecastList.showIf { state } }
            })

            forecastList.observe(viewLifecycleOwner, { data ->
                val state = viewModel.currentState.value ?: ""
                val capital = viewModel.currentCapital.value ?: ""
                adapter = WeatherForecastAdapter(capital, state)
                adapter.submitList(data)
                binding.forecastList.adapter = adapter
            })

            loadingVisibility.observe(viewLifecycleOwner, { visibility ->
                binding.apply { loader.progressOverlay.visibility = visibility }
            })

            errorMessage.observeForever {
                if (!it.hasBeenHandled)
                    Toast.makeText(requireContext(), it.getContentIfNotHandled(), Toast.LENGTH_LONG).show()
            }
        }
    }
}