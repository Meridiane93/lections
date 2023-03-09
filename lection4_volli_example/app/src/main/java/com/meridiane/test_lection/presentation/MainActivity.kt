package com.meridiane.test_lection.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.android.volley.toolbox.Volley
import com.meridiane.test_lection.databinding.ActivityMainBinding
import com.meridiane.test_lection.domain.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonGetCountry.setOnClickListener {
            getWeather()
        }

        viewModel.liveAdsData.observe(this) {
            binding.textTitle.text = it
        }

        viewModel.liveAdsDataError.observe(this) {
            binding.textGetCountry.error = it
        }
    }

    private fun getWeather() {

        val text = binding.textGetCountry.text?.filter { !it.isWhitespace() }

        val queue = Volley.newRequestQueue(this)

        if (text!!.isEmpty()) binding.textGetCountry.error = "Поле не заполнено"
        else viewModel.getWeather(binding.textGetCountry.text.toString(),queue)

    }

}