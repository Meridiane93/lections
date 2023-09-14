package com.example.server1.data

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerRepository() {

    //создание ретрофита
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pushappsposharka.shn-host.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServerApi::class.java)

    suspend fun getLink() {
        val response = retrofit.getLink()
        Log.d("MyTag","response code: $response")
    }

}

