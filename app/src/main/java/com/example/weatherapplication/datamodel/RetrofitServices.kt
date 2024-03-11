package com.example.weatherapplication.datamodel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("/v1/current.json?key=0e34cb7e81824a78bde81230241103")
    suspend fun getWeatherDetail(
        @Query("q") city:String
    ):Response<WeatherResponseModel>
}