package com.example.weatherapp

import com.example.weatherapp.BuildConfig.SERVER_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getWeatherOfCity(
        @Query("q") q: String,
        //@Query("units") units: String = "metric",
        @Query("appid") appid: String = "8e993348cb9f2efd00ff764b4f5f43eb",
        @Query("lang") lang: String = "ru"
    ): Response<WeatherResponse>
//    companion object {
//        var apiService: ApiService? = null
//        fun getInstance() : ApiService {
//            if (apiService == null) {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl(SERVER_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                apiService = retrofit.create(ApiService::class.java)
//            }
//            return apiService!!
//        }
//
//    }
}