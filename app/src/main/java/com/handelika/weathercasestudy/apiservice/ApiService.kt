package com.handelika.weathercasestudy.apiservice

import com.handelika.weathercasestudy.const.Constants.API_KEY
import com.handelika.weathercasestudy.models.ForecastDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("posts/{id}")
    fun getPostById(@Path("id") postId: Int): Call<GET>

    //get weather data
    @GET("weather.ashx")
    fun getForecast(
        @Query("key") key: String = API_KEY,
        @Query("num_of_days") num_of_days: Int,
        @Query("format") format: String = "json",
        @Query("q") q: String, // location name
    ): Call<ForecastDataResponse>
}