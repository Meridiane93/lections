package com.example.server1.data



import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ServerApi {
    @GET("examinator_meridiane.php?installType=organic")
    suspend fun getLink(): Response<Answer>

}