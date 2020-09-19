package com.example.weather.ui.extended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.ExtendedInformationBinding
import com.example.weather.networking.model.ExtendedWeatherData
import com.example.weather.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExtendedInformationFragment : Fragment() {
    private var _binding: ExtendedInformationBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherViewModel: WeatherViewModel
    private val recAdapter = RecAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ExtendedInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        setUpRecyclerView()
        subscribeObserver()
    }

    private fun setUpRecyclerView() {
        binding.recId.layoutManager = LinearLayoutManager(context)
        binding.recId.adapter = recAdapter
    }

    private fun subscribeObserver() {
        weatherViewModel.getExtendedWeather().observe(viewLifecycleOwner, Observer {
            it?.let { submitDataToRecyclerView(it.extendedWeatherData) }
        })
    }

    private fun submitDataToRecyclerView(extendedWeatherData: ExtendedWeatherData) {
        recAdapter.submitList(extendedWeatherData.list)
        hideProgressBar()
    }

    private fun hideProgressBar() {
        binding.recId.visibility = View.VISIBLE
        binding.load.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
