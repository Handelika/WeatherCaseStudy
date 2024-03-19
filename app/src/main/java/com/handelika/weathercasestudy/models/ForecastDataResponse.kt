package com.handelika.weathercasestudy.models

data class ForecastDataResponse (
    val data: Data? = null
)

data class Data (
    val request: List<Request>? = null,
    val currentCondition: List<CurrentCondition>? = null,
    val weather: List<WeatherElement>? = null,
    val climateAverages: List<ClimateAverage>? = null
)

data class ClimateAverage (
    val month: List<Month>? = null
)

data class Month (
    val index: String? = null,
    val name: String? = null,
    val avgMinTemp: String? = null,
    val avgMinTempF: String? = null,
    val absMaxTemp: String? = null,
    val absMaxTempF: String? = null,
    val avgDailyRainfall: String? = null
)

data class CurrentCondition (
    val observationTime: String? = null,
    val tempC: String? = null,
    val tempF: String? = null,
    val weatherCode: String? = null,
    val weatherIconURL: List<Weather>? = null,
    val weatherDesc: List<Weather>? = null,
    val windspeedMiles: String? = null,
    val windspeedKmph: String? = null,
    val winddirDegree: String? = null,
    val winddir16Point: String? = null,
    val precipMM: String? = null,
    val precipInches: String? = null,
    val humidity: String? = null,
    val visibility: String? = null,
    val visibilityMiles: String? = null,
    val pressure: String? = null,
    val pressureInches: String? = null,
    val cloudcover: String? = null,
    val feelsLikeC: String? = null,
    val feelsLikeF: String? = null,
    val uvIndex: String? = null
)

data class Weather (
    val value: String? = null
)

data class Request (
    val type: String? = null,
    val query: String? = null
)

data class WeatherElement (
    val date: String? = null,
    val astronomy: List<Astronomy>? = null,
    val maxtempC: String? = null,
    val maxtempF: String? = null,
    val mintempC: String? = null,
    val mintempF: String? = null,
    val avgtempC: String? = null,
    val avgtempF: String? = null,
    val totalSnowCM: String? = null,
    val sunHour: String? = null,
    val uvIndex: String? = null,
    val hourly: List<Hourly>? = null
)

data class Astronomy (
    val sunrise: String? = null,
    val sunset: String? = null,
    val moonrise: String? = null,
    val moonset: String? = null,
    val moonPhase: String? = null,
    val moonIllumination: String? = null
)

data class Hourly (
    val time: String? = null,
    val tempC: String? = null,
    val tempF: String? = null,
    val windspeedMiles: String? = null,
    val windspeedKmph: String? = null,
    val winddirDegree: String? = null,
    val winddir16Point: String? = null,
    val weatherCode: String? = null,
    val weatherIconURL: List<Weather>? = null,
    val weatherDesc: List<Weather>? = null,
    val precipMM: String? = null,
    val precipInches: String? = null,
    val humidity: String? = null,
    val visibility: String? = null,
    val visibilityMiles: String? = null,
    val pressure: String? = null,
    val pressureInches: String? = null,
    val cloudcover: String? = null,
    val heatIndexC: String? = null,
    val heatIndexF: String? = null,
    val dewPointC: String? = null,
    val dewPointF: String? = null,
    val windChillC: String? = null,
    val windChillF: String? = null,
    val windGustMiles: String? = null,
    val windGustKmph: String? = null,
    val feelsLikeC: String? = null,
    val feelsLikeF: String? = null,
    val chanceofrain: String? = null,
    val chanceofremdry: String? = null,
    val chanceofwindy: String? = null,
    val chanceofovercast: String? = null,
    val chanceofsunshine: String? = null,
    val chanceoffrost: String? = null,
    val chanceofhightemp: String? = null,
    val chanceoffog: String? = null,
    val chanceofsnow: String? = null,
    val chanceofthunder: String? = null,
    val uvIndex: String? = null,
    val shortRAD: String? = null,
    val diffRAD: String? = null
)
