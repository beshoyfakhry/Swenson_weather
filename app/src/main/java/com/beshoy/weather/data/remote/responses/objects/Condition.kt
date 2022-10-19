package com.beshoy.weather.data.remote.responses.objects

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
) {
    fun getIconLink(): String {
        return "http:$icon"
    }
}