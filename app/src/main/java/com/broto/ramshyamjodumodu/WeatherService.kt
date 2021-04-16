package com.broto.ramshyamjodumodu

import com.broto.ramshyamjodumodu.weatherModels.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("forecasts/v1/daily/5day/{locationkey}")
    fun getWeatherDataRAW(
        @Path("locationkey") locationKey: String,
        @Query("apikey") apikey: String,
        @Query("details") details: Boolean,
        @Query("metric") metric: Boolean): Call<WeatherResponse>

}