package com.example.weather.ui.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.databinding.MainInformationBinding
import com.example.weather.networking.model.CurrentWeatherData
import com.example.weather.ui.WeatherViewModel
import com.example.weather.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.text.SimpleDateFormat as SimpleDateFormat1

@AndroidEntryPoint
class MainInformationFragment : Fragment() {
    private var _binding: MainInformationBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
    }

    private fun setUpObserver() {
        weatherViewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            it?.let { showData(it.currentWeatherData) }
        })
    }

    private fun showData(currentWeatherData: CurrentWeatherData) {
        binding.temp.text = getString(R.string.celsius_with_args, currentWeatherData.main.temp)
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
            getString(R.string.feelsLike_celsius, currentWeatherData.main.feels_like)
        binding.city.text = currentWeatherData.name
        binding.pressure.text =
            getString(R.string.pressure_with_args, currentWeatherData.main.pressure)
        binding.humidity.text =
            getString(R.string.humidity_with_args, currentWeatherData.main.humidity, "%")
        binding.wind.text =
            getString(R.string.speed_with_args, currentWeatherData.wind.speed.toString())
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