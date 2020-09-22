package com.example.weatherapp.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import com.example.movieshop.ui.common.BaseFragment
import com.example.weatherapp.BuildConfig.HELP_URL
import com.example.weatherapp.databinding.HelpFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HelpFragment : BaseFragment<HelpFragmentBinding>() {
    private lateinit var frameLayout: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HelpFragmentBinding.inflate(inflater)
        frameLayout = binding.progress.progressOverlay
        frameLayout.visibility = VISIBLE
        binding.helpWebView.loadUrl(HELP_URL)
        binding.helpWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress == 100) {
                    frameLayout.visibility= GONE
                }
            }
        }
        return binding.root
    }
}