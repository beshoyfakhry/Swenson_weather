package com.beshoy.weather.repositories

import android.util.Log
import com.beshoy.weather.data.remote.WeatherApi
import com.beshoy.weather.data.remote.responses.response.CityNameResponse
import com.beshoy.weather.data.remote.responses.response.WeatherResponseObject
import com.beshoy.weather.other.Resource
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {


    override suspend fun getForecast(cityName: String): Resource<WeatherResponseObject> {
        return try {
            val response = weatherApi.getForecast(cityName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Log.e("EXCEPTION", "EXCEPTION:", e)
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun getCitiesNamesList(cityName: String): Resource<CityNameResponse> {
        return try {
            val response = weatherApi.getCitiesNamesList(cityName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Log.e("EXCEPTION", "EXCEPTION:", e)
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}














