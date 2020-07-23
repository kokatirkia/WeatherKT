package com.example.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weather.databinding.MainInformationBinding
import com.example.weather.networking.model.Constants
import com.example.weather.networking.model.CurrentWeatherData
import com.example.weather.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.text.SimpleDateFormat as SimpleDateFormat1

@AndroidEntryPoint
class MainInformationFragment : Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel
    private var _binding: MainInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        weatherViewModel =
            ViewModelProvider(this@MainInformationFragment).get(WeatherViewModel::class.java)

        subscribeObserver()
    }

    private fun subscribeObserver() {
        weatherViewModel.getCurrentWeather()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if(it!=null) {
                    showData(it.currentWeatherData)
                }
            })
    }

    private fun showData(currentWeatherData: CurrentWeatherData) {
        binding.temp.text = currentWeatherData.main.temp + "°C"
        binding.description.text =
            currentWeatherData.weather[0].description.capitalize()
        binding.sunset.text = SimpleDateFormat1(
            "hh:mm a",
            Locale.ENGLISH
        ).format(Date(currentWeatherData.sys.sunset * 1000))
        binding.sunrise.text = SimpleDateFormat1(
            "hh:mm a",
            Locale.ENGLISH
        ).format(Date(currentWeatherData.sys.sunrise * 1000))
        binding.feelsLike.text =
            "Feels like: " + currentWeatherData.main.feels_like + "°C"
        binding.city.text = currentWeatherData.name
        binding.pressure.text = currentWeatherData.main.pressure.toString() + " mBar"
        binding.humidity.text = currentWeatherData.main.humidity.toString() + "%"
        binding.wind.text = currentWeatherData.wind.speed.toString() + " km/h"
        Glide.with(binding.rootLayout)
            .load(Constants.iconUrl + currentWeatherData.weather[0].icon + ".png")
            .into(binding.iconid)

        hideProgressbar()
    }

    private fun hideProgressbar() {
        binding.mainInformationGroup.visibility = View.VISIBLE
        binding.mainInformationProgressbar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}