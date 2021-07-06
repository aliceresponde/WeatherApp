package com.example.weatherapp.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ConfigFragmentBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigFragment : BaseFragment<ConfigFragmentBinding>() {
    private val viewModel: ConfigViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConfigFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        when (viewModel.getCurrentSystem()) {
            METRIC -> binding.radioGroup.check(R.id.metricSystemRadio)
            IMPERIAL -> binding.radioGroup.check(R.id.imperialSystemRadio)
            else -> { }
        }

        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.metricSystemRadio -> viewModel.updateSystemPreference(METRIC)
                R.id.imperialSystemRadio -> viewModel.updateSystemPreference(IMPERIAL)
                else -> { }
            }
        }

        viewModel.isChangeReady.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), getString(R.string.changes_done), Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        private const val IMPERIAL: String = "Imperial"
        private const val METRIC: String = "Metric"
    }

}