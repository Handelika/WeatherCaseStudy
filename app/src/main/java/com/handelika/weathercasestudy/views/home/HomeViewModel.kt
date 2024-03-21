package com.handelika.weathercasestudy.views.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.handelika.weathercasestudy.apiservice.ApiClient
import com.handelika.weathercasestudy.models.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _weather = mutableStateOf(WeatherData())
    val weather: State<WeatherData> = _weather

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    fun getWeatherData(query: String) {
        _isLoading.value = true

        val call: Call<WeatherData> = ApiClient.apiService.getForecast(
            num_of_days = 5, q = query
        )

        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(
                call: Call<WeatherData>,
                response: Response<WeatherData>
            ) {
                if (response.isSuccessful) {

                    // success response
                    if (response.body() != null) {
                        Log.d("weatherdata query", query)

                        Log.d("weatherdata res", response.body().toString())
                        _weather.value = response.body()!!
                        _isLoading.value = false
                    } else {
                        //error
                        _isLoading.value = true
                    }
                } else {
                    // response error
                    println("weather_error: Kod: ${response.code()}")
                    _isLoading.value = true
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                // response fail
                println("weather_failure: ${t.message}")
                _isLoading.value = true

            }
        })
    }
}