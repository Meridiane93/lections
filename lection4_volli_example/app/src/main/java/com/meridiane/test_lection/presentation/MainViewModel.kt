package com.meridiane.test_lection.presentation

import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject

const val API_KEY = "80154f121203463dbce195220230703"

class MainViewModel: ViewModel() {

    val flow = MutableStateFlow("")

    val flowError = MutableStateFlow("")

    fun getWeather(country:String,queue: RequestQueue) {

        val url = "https://api.weatherapi.com/v1/current.json?key=$API_KEY&q=$country&aqi=no"

        val stringRequest =  StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val jsonOb = JSONObject(response)
                val currentWeather = jsonOb.getJSONObject("current")
                val locationWeather = jsonOb.getJSONObject("location")
                val temperature = currentWeather.getString("temp_c")
                val location = locationWeather.getString("name")

                flow.value = "город: $location температура: $temperature"
            },
            { error ->
                flowError.value = "$error"
            }
        )
        queue.add(stringRequest)

    }

}