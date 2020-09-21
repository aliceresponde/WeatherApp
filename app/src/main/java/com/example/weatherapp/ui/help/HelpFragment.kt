package com.example.weatherapp.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.BuildConfig.HELP_URL
import com.example.weatherapp.R
import com.example.weatherapp.databinding.HelpFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelpFragment : BaseFragment<HelpFragmentBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HelpFragmentBinding.inflate(inflater)
        binding.helpWebView.loadUrl(HELP_URL)
        return binding.root
    }
}