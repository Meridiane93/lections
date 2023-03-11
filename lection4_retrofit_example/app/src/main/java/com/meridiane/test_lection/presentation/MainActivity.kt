package com.meridiane.test_lection.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.meridiane.test_lection.databinding.ActivityMainBinding
import com.meridiane.test_lection.dэata.UiState
import kotlinx.coroutines.launch
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonGetCountry.setOnClickListener {
            getWeather()
        }

        lifecycleScope.launch {

            viewModel.uiState.collect { data ->
                textMain(data)
            }

        }

    }

    private fun textMain(data:UiState)  {
        if (data.name != null) {
             binding.textTitle.text =
                if (data.main?.temp!!.toInt() > 100) "${data.name} : ${farengate(data.main.temp)}"
                else "${data.name} : ${data.main.temp}"

        } else {
            binding.textGetCountry.error = "${data.cod}"
        }
    }

    private fun getWeather() =
        viewModel.getWeather(binding.textGetCountry.text.toString())

    private fun farengate(fTemp:Double): String =
        ( fTemp.toFloat() - 273f ).toBigDecimal().setScale(2, RoundingMode.UP).toString()

}

