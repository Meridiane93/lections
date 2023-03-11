package com.meridiane.test_lection.domain

import com.meridiane.test_lection.data.WeatherAPI
import com.meridiane.test_lection.data.WeatherModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/"

class Repository {

   private val retrofit = Retrofit.Builder()
       .baseUrl(BASE_URL)
       .addConverterFactory(GsonConverterFactory.create())
       .build()
       .create(WeatherAPI::class.java)

    suspend fun getDataService(cityName: String): WeatherModel {
        return retrofit.getData(cityName)
    }

    suspend fun getCoordinateService(lat: String, lot:String): WeatherModel {
        return retrofit.getCoordinate(lat,lot)
    }

}