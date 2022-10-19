package com.beshoy.weather.data.remote.responses.objects

data class CityNameResponseItem(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)
{

    override fun toString(): String {
        return  "$name - $country"
    }
}