package com.handelika.weathercasestudy.views.search

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.handelika.weathercasestudy.apiservice.ApiClient
import com.handelika.weathercasestudy.data.DataMode
import com.handelika.weathercasestudy.models.WeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel(){

   private val _textState = mutableStateOf("")
    val textState : State<String> = _textState

    var _datamode: MutableState<DataMode<WeatherData, Boolean, Throwable>> = mutableStateOf(value = DataMode(loading = false))
    var data: State<DataMode<WeatherData, Boolean, Throwable>> = _datamode

    private val _weather = mutableStateOf(WeatherData())
    val weather: State<WeatherData> = _weather

    private val _weatherList = mutableStateListOf<WeatherData>()
    val weatherList: List<WeatherData> = _weatherList

    init {
        _weatherList.addAll(getList())
    }

    private fun getWeatherData(query:String) {

        _datamode.value.loading = true

        //api call
        val call: Call<WeatherData> = ApiClient.apiService.getForecast(
            num_of_days = 5, q = query
        )

        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(
                call: Call<WeatherData>,
                response: Response<WeatherData>
            ) {
                if (response.isSuccessful) {
                    Log.d("weatherdata res", "${response.body()!!.data}")

                    // Başarılı cevap durumunda kullanıcı verisini işleyin
                    if (response.body() != null) {
                        _weather.value = response.body()!!
                        _datamode.value.data = weather.value
                        _datamode.value.loading = false
                    } else {
                        _datamode.value.loading = true
                    }
                } else {
                    // request error
                    println("weather_error: Kod: ${response.code()}")
                    _datamode.value.loading = true
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                // for request fail
                println("weather_failure: ${t.message}")
                _datamode.value.loading = true
                _datamode.value.e = t
            }
        })

    }

    fun searchData(){
        viewModelScope.launch {
            if(_weather.value.data != null && !_weather.value.data!!.request.isNullOrEmpty()){
                _textState.value = _weather.value.data!!.request!!.first()!!.query!!
            }
        }
    }

    fun addData(){
        viewModelScope.launch {
            _weatherList.add(_weather.value)

            //clearing texts after adding
            _weather.value = WeatherData()
            _textState.value = ""

        }
    }

    fun removeData(index :Int){
        viewModelScope.launch {
            _weatherList.remove(_weatherList[index])
        }
    }

    fun fetchData(query: String) {

        viewModelScope.launch {
            getWeatherData(query)

            _datamode.value.loading = false
        }

    }

   fun getList () : List<WeatherData> {
        return weatherList
    }


    //Handling back button
    val backPressedEvent = MutableStateFlow<Boolean>(false)

    fun onBackPressed() {
        backPressedEvent.value = false
    }

}

