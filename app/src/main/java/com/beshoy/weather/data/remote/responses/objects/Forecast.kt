package com.beshoy.weather.data.remote.responses.objects

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecastList: List<ForecastDay>
)