package com.example.weather.ui.extended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.ExtendedInformationBinding
import com.example.weather.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExtendedInformationFragment : Fragment() {
    private var _binding: ExtendedInformationBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private lateinit var recAdapter: RecAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExtendedInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        subscribeObserver()
    }

    private fun setUpRecyclerView() {
        recAdapter = RecAdapter()
        binding.recId.layoutManager = LinearLayoutManager(context)
        binding.recId.adapter = recAdapter
    }

    private fun subscribeObserver() {
        weatherViewModel.weather.observe(viewLifecycleOwner, Observer {
            it?.let {
                recAdapter.submitList(it.extendedWeather.list)
                hideProgressBar()
            }
        })
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
