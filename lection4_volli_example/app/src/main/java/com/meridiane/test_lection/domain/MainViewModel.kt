package com.meridiane.test_lection.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject

const val API_KEY = "80154f121203463dbce195220230703"

class MainViewModel: ViewModel() {

    val liveAdsData = MutableLiveData<String>()

    val liveAdsDataError = MutableLiveData<String>()

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

                liveAdsData.value = "город: $location температура: $temperature"
            },
            { error ->
                liveAdsDataError.value = "$error"
            }
        )
        queue.add(stringRequest)

    }

}