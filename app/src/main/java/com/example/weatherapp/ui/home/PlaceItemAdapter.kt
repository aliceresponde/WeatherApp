package com.example.weatherapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.PlaceItemLayoutBinding
import com.example.weatherapp.domain.model.PlaceItem

class PlaceItemAdapter(
    private var places: List<PlaceItem> = listOf(),
    private var onItemClickListener: (PlaceItem) -> Unit
) : RecyclerView.Adapter<PlaceItemAdapter.PlaceItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.place_item_layout, parent, false)
        return PlaceItemHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceItemHolder, position: Int) {
        holder.bing(places[position])
    }

    override fun getItemCount(): Int = places.size

    fun updateData (list: List<PlaceItem>){
        places = list
        notifyDataSetChanged()
    }

    inner class PlaceItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PlaceItemLayoutBinding.bind(itemView)

        fun bing(placeItem: PlaceItem) {
            binding.placeItemText.text = placeItem.toString()
            itemView.setOnClickListener { onItemClickListener(placeItem) }
        }
    }
}
