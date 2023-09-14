package com.example.server1.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.server1.R
import com.example.server1.data.ServerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val agent = getDefaultUserAgent()
        Log.d("MyTag","agent: $agent")

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->

                val requestBuilder = chain.request().newBuilder()
                    .header("User-Agent",agent)

                chain.proceed(requestBuilder.build())
            }.build()

        //создание ретрофита
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pushappsposharka.shn-host.ru/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerApi::class.java)

        suspend fun getLink() {
            try{
                val response = retrofit.getLink()
                Log.d("MyTag","response code: $response")
            }catch (e:Exception) {
                Log.d("MyTag","response code: $e")
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            getLink()
        }

    }

    fun getDefaultUserAgent(): String {
        val result = StringBuilder(64)
        result.append("Dalvik/")
        result.append(System.getProperty("java.vm.version")) // such as 1.1.0
        result.append(" (Linux; U; Android ")
        val version = Build.VERSION.RELEASE // "1.0" or "3.4b5"
        result.append(if (version.length > 0) version else "1.0")

        // add the model for the release build
        if ("REL" == Build.VERSION.CODENAME) {
            val model = Build.MODEL
            if (model.length > 0) {
                result.append("; ")
                result.append(model)
            }
        }
        val id = Build.ID // "MASTER" or "M4-rc20"
        if (id.length > 0) {
            result.append(" Build/")
            result.append(id)
        }
        result.append(")")
        return result.toString()
    }
}