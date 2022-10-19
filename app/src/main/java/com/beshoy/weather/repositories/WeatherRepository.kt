package com.beshoy.weather.repositories

import com.beshoy.weather.data.remote.responses.response.CityNameResponse
import com.beshoy.weather.data.remote.responses.response.WeatherResponseObject
import com.beshoy.weather.other.Resource
import retrofit2.http.Query

interface WeatherRepository {


    suspend fun getForecast(
        cityName: String
    ): Resource<WeatherResponseObject>


    suspend fun getCitiesNamesList(
        @Query("q") cityName: String
    ): Resource<CityNameResponse>
}