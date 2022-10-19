package com.beshoy.weather.data.remote

import com.beshoy.weather.BuildConfig
import com.beshoy.weather.data.remote.responses.response.CityNameResponse
import com.beshoy.weather.data.remote.responses.response.WeatherResponseObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") cityName: String,
        @Query("days") days: Int = 3,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<WeatherResponseObject>

    @GET("search.json")
    suspend fun getCitiesNamesList(
        @Query("q") cityName: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<CityNameResponse>
}