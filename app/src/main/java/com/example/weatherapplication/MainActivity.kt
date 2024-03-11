package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapplication.datamodel.RetrofitBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory
    private lateinit var repo: Repo
    private lateinit var loader:ProgressBar
    private lateinit var etCity:EditText
    private lateinit var btnGetWeather:Button
    private lateinit var ivWeather:ImageView
    private lateinit var tvWeather:TextView
    private lateinit var tvCityState:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        btnGetWeather.setOnClickListener {
            weatherViewModel.getWeatherDetail(etCity.text.toString())
        }
        weatherViewModel.weatherDetailLive.observe(this){
            Log.i("WeatherDetail",it.toString())
            val currentWeatherType=it.current.condition.text
            val currentTempInC=it.current.temp_c

            tvWeather.text="$currentWeatherType,$currentTempInC"

            val imgLink="https:${it.current.condition.icon}"
            Glide.with(this)
                .load(imgLink)
                .into(ivWeather)
            val cityName=it.location.name
            val stateName=it.location.region
            tvCityState.text="$cityName,$stateName"
        }
        weatherViewModel.isLoading.observe(this){
            if (it){
                loader.visibility= View.VISIBLE
            }
            else{
                loader.visibility=View.GONE
            }
        }
    }
    private fun init() {
        repo=Repo(RetrofitBuilder.getInstance())
        weatherViewModelFactory= WeatherViewModelFactory(repo)
        weatherViewModel=ViewModelProvider(this,weatherViewModelFactory).get(WeatherViewModel::class.java)
        loader=findViewById(R.id.loader)
        etCity=findViewById(R.id.et_city_name)
        btnGetWeather=findViewById(R.id.btn_get_weather)
        ivWeather=findViewById(R.id.iv_weather)
        tvWeather=findViewById(R.id.tv_weather)
        tvCityState=findViewById(R.id.tv_city_state)

    }
}