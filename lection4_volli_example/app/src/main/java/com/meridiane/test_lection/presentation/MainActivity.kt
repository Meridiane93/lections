package com.meridiane.test_lection.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.volley.toolbox.Volley
import com.meridiane.test_lection.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            viewModel.flow.collect {
                binding.textTitle.text = it
            }
        }

        lifecycleScope.launch {
            viewModel.flowError.collect {
                binding.textGetCountry.error = it
            }
        }
    }

    private fun getWeather() {

        val text = binding.textGetCountry.text?.filter { !it.isWhitespace() }

        val queue = Volley.newRequestQueue(this)

        if (text!!.isEmpty()) binding.textGetCountry.error = "Поле не заполнено"
        else viewModel.getWeather(binding.textGetCountry.text.toString(),queue)

    }

}