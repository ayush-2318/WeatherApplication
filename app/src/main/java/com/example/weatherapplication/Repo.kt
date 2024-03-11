package com.example.weatherapplication

import com.example.weatherapplication.datamodel.RetrofitServices

class Repo(
    private val retrofitServices: RetrofitServices
) {
    suspend fun getWeatherDetail(city:String)=retrofitServices.getWeatherDetail(city)
}