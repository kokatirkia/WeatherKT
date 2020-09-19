package com.example.weather.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.ui.current.MainInformationFragment
import com.example.weather.ui.extended.ExtendedInformationFragment
import com.example.weather.utils.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var preferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        Constants.CITY = preferences.getString("city", "Tbilisi").toString()

        setUpFragments()
        subscribeApiResponseMessageObserver()
        keyboardEnterKeyClickEvent()
        fetchWeatherData()
        setUpOnClickListener()
    }

    private fun setUpOnClickListener() {
        binding.searchButton.setOnClickListener {
            if (binding.enterAddress.text.toString().isNotEmpty()) {
                Constants.CITY = binding.enterAddress.text.toString()
                preferences.edit().putString("city", binding.enterAddress.text.toString()).apply()
                hideKeyboard()
                fetchWeatherData()
            }
        }
    }

    private fun subscribeApiResponseMessageObserver() {
        weatherViewModel.getApiResponseMessage().observe(this, Observer {
            if (it.isNotEmpty()) showSnackbarMessage(it)
        })
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(
            binding.mainActivityContainer,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.enterAddress.windowToken, 0)
    }

    private fun keyboardEnterKeyClickEvent() {
        binding.enterAddress.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.searchButton.performClick()
                true
            } else {
                false
            }
        }
    }

    private fun fetchWeatherData() {
        weatherViewModel.fetchWeatherData()
    }

    private fun setUpFragments() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MainInformationFragment(), "Current")
        adapter.addFragment(ExtendedInformationFragment(), "5 days")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}