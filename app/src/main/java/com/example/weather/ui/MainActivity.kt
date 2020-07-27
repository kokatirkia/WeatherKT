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
import com.example.weather.utils.Constants
import com.example.weather.ui.fragments.ExtendedInformationFragment
import com.example.weather.ui.fragments.MainInformationFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        pref = applicationContext.getSharedPreferences("MyPref", 0)
        Constants.CITY = pref.getString("city", "Tbilisi").toString()

        setUpFragments()
        subscribeApiResponseMessageObserver()
        keyboardEnterClickEvent()

        fetchWeatherData()

        binding.searchButton.setOnClickListener {
            if (binding.enterAddress.text.toString().isNotEmpty()) {
                Constants.CITY = binding.enterAddress.text.toString()
                pref.edit().putString("city", binding.enterAddress.text.toString()).apply()
                hideKeyboard()
                fetchWeatherData()
            }
        }
    }

    private fun subscribeApiResponseMessageObserver() {
        weatherViewModel.getApiResponseMessage().observe(this, Observer {
            if (it == "!success") {
                showSnackbarMessage("City not found, enter valid city!")
            } else if (it == "noInternetConnection")
                showSnackbarMessage("No internet connection!")
            else showSnackbarMessage("Data updated!")
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

    private fun keyboardEnterClickEvent() {
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