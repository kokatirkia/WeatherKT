package com.example.weather.ui.extended

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.databinding.RowLayoutBinding
import com.example.weather.ui.model.WeatherExtendedDataUi
import com.example.weather.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class RecAdapter : ListAdapter<WeatherExtendedDataUi, RecAdapter.CustomViewHolder>(
    DiffCallback()
) {

    private class DiffCallback : DiffUtil.ItemCallback<WeatherExtendedDataUi>() {
        override fun areItemsTheSame(
            oldItem: WeatherExtendedDataUi,
            newItem: WeatherExtendedDataUi
        ): Boolean {
            return oldItem.dt_txt == newItem.dt_txt
        }

        override fun areContentsTheSame(
            oldItem: WeatherExtendedDataUi,
            newItem: WeatherExtendedDataUi
        ): Boolean {
            return oldItem.main.temp == newItem.main.temp &&
                    oldItem.main.humidity == newItem.main.humidity &&
                    oldItem.main.pressure == newItem.main.pressure &&
                    oldItem.wind.speed == newItem.wind.speed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            RowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val extendedWeather = getItem(position)
        holder.binding.time.text = SimpleDateFormat(
            "EEE, d MMM HH:mm",
            Locale.ENGLISH
        ).format(Date(extendedWeather.dt * 1000))
        holder.binding.temp.text = extendedWeather.main.temp.toString() + "°C"
        holder.binding.description.text =
            extendedWeather.weather[0].description.capitalize()
        holder.binding.pressure.text = extendedWeather.main.pressure + " mBar"
        holder.binding.humidity.text = extendedWeather.main.humidity + "%"
        holder.binding.wind.text = extendedWeather.wind.speed.toString() + " km/h"
        Glide.with(holder.binding.root.context)
            .load(Constants.iconUrl + extendedWeather.weather[0].icon + ".png")
            .into(holder.binding.icon)

        val isExpandable: Boolean = getItem(position).expanded
        holder.binding.rowViewGroup.visibility = if (isExpandable) View.VISIBLE else View.GONE

    }

    inner class CustomViewHolder(val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.container.setOnClickListener {
                val extendedW = getItem(adapterPosition)
                extendedW.expanded = !extendedW.expanded
                notifyItemChanged(adapterPosition)
            }
        }
    }
}