package com.handelika.weathercasestudy.views.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.handelika.weathercasestudy.apiservice.ApiClient
import com.handelika.weathercasestudy.models.ForecastDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _weather = mutableStateOf(ForecastDataResponse())
    val weather: State<ForecastDataResponse> = _weather


//    var datamode: MutableState<DataMode<ForecastDataResponse, Boolean, Exception>> = mutableStateOf(value = DataMode(loading = false))
//     var data: State<DataMode<ForecastDataResponse, Boolean, Exception>> = datamode

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

     fun getWeatherData(locationName: String) {
        val call: Call<ForecastDataResponse> = ApiClient.apiService.getForecast(
            num_of_days = 5, q = locationName
        )

        call.enqueue(object : Callback<ForecastDataResponse> {
            override fun onResponse(
                call: Call<ForecastDataResponse>,
                response: Response<ForecastDataResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("weatherdata", "${response.body()!!.data}")

                    // Başarılı cevap durumunda kullanıcı verisini işleyin
                    if (response.body() != null) {
                        _weather.value = response.body()!!
                        _weather.value = weather.value
                        _isLoading.value = false
                    } else {
                        _isLoading.value = true
                    }
                } else {
                    // Başarısız cevap durumunda hata mesajı gösterin
                    println("weather_error: Kod: ${response.code()}")
                    _isLoading.value = true
                }
            }

            override fun onFailure(call: Call<ForecastDataResponse>, t: Throwable) {
                // İstek gönderilirken bir hata oluşursa hata mesajı gösterin
                println("weather_failure: ${t.message}")
                _isLoading.value = true

            }
        })

    }

    @Composable
    fun FetchData() {

        LaunchedEffect(true) {
//        withContext(Dispatchers.IO) {
//        }
            getWeatherData("İzmir")

            kotlinx.coroutines.delay(2000L)
            _isLoading.value = false

        }
    }

}