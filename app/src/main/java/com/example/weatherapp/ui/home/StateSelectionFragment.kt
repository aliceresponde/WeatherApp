package com.example.weatherapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.StateSelectionFragmentBinding
import com.example.weatherapp.ui.cities.CitiesViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StateSelectionFragment : BaseFragment<StateSelectionFragmentBinding>(),
    OnItemSelectedListener {

    private val stateList by lazy { resources.getStringArray(R.array.states) }
    private val capitalList by lazy { resources.getStringArray(R.array.capital) }
    private val viewModel: CitiesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StateSelectionFragmentBinding.inflate(inflater)
        initObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.capital,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.statesSpinner.adapter = adapter
        }

        binding.apply {
            statesSpinner.onItemSelectedListener = this@StateSelectionFragment
        }
    }

    private fun initObservers() {
        viewModel.apply {
            currentCapital.observe(viewLifecycleOwner) {
                binding.capital = it
            }
            currentState.observe(viewLifecycleOwner) {
                binding.state = it
            }
            currentCapital.observe(viewLifecycleOwner) {
                viewModel.getCurrentWeatherByLocationName(it)
            }
            showCurrentWeatherCard.observe(viewLifecycleOwner) {
                setVisibility(binding.currentWeather, it)
            }
            currentWeather.observe(viewLifecycleOwner) {
                binding.item = it
            }

            errorMessage.observe(viewLifecycleOwner){
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.setCurrentState(stateList[position])
        viewModel.setCurrentCapital(capitalList[position])
        viewModel.getCurrentWeatherByLocationName(capitalList[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        binding.currentWeather.visibility = GONE
    }

    private fun setVisibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) VISIBLE else GONE
    }
}