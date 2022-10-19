package com.beshoy.weather.data.remote.responses.objects

import com.google.gson.annotations.SerializedName

data class Day(

    val condition: Condition,

    @SerializedName("avghumidity")
    private val humidity: Double,

    @SerializedName("maxtemp_f")
    private val tempMaxF: Double,

    @SerializedName("mintemp_f")
    private val tempMinF: Double,

    @SerializedName("avgtemp_f")
    private val avgTempF: Double,

    @SerializedName("maxwind_mph")
    private val windMaxMile: Double


) {

    fun getTheDayMinAndMaxTemp(): String {
        return "$tempMinF°F/$tempMaxF°F"
    }

    fun getAvgTempF(): String {
        return "${avgTempF} °F"
    }

    fun getWindSpeed(): String {
        return "$windMaxMile mph"
    }

    fun getHumidity(): String {
        return "$humidity %"
    }
}