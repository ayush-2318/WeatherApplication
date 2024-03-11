package com.example.weatherapplication

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.datamodel.WeatherResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val repo: Repo):ViewModel() {
    val weatherDetailLive=MutableLiveData<WeatherResponseModel>()
    val isLoading=MutableLiveData<Boolean>(false)
    fun getWeatherDetail(city:String){
        viewModelScope.launch (Dispatchers.IO){
            // network request is send
            // make loader visible
            isLoading.postValue(true)
            val response=repo.getWeatherDetail(city)
            if(response.isSuccessful){
                weatherDetailLive.postValue(response.body())
                // data get from server
                // hide the loader
                isLoading.postValue(false)
            }

        }

    }
}