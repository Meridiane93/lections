package com.meridiane.test_lection.data

import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "04a42b96398abc8e4183798ed22f9485"

interface WeatherAPI {

    @GET("data/2.5/weather?&units=metric&APPID=$API_KEY")
    suspend fun getData(
        @Query("q") cityName: String
    ): WeatherModel

    @GET("/data/2.5/weather?&&APPID=$API_KEY")
    suspend fun getCoordinate(
        @Query("lat") lat:String,
        @Query("lon") lon:String
    ) : WeatherModel

}