package com.example.weatherapp.ui.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ForecastWeatherCardBinding
import com.example.weatherapp.domain.model.ForecastWeatherItem

class WeatherForecastAdapter(
    private var capital: String = "",
    private var state: String = ""
): ListAdapter<ForecastWeatherItem, WeatherForecastAdapter.ViewHolder>(WeatherForecastDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun from(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ForecastWeatherCardBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ForecastWeatherCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forecastWeatherItem: ForecastWeatherItem) {
            binding.item = forecastWeatherItem
            binding.state = state
            binding.capital = capital
            binding.executePendingBindings()
        }
    }

    class WeatherForecastDiffCallBack : DiffUtil.ItemCallback<ForecastWeatherItem>() {
        override fun areItemsTheSame(
            oldItem: ForecastWeatherItem,
            newItem: ForecastWeatherItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ForecastWeatherItem,
            newItem: ForecastWeatherItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
