package com.beshoy.weather.other

import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.today(): String {

    val calendar = Calendar.getInstance()

    val date = calendar.time.time

    val weekDay = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date)
    val todayDate = SimpleDateFormat("dd MMM yyyy ", Locale.ENGLISH).format(date)
    return "$weekDay,$todayDate"

}

fun Fragment.dayAfterTomorrow(): String {
    val gc = Calendar.getInstance()
    gc.add(Calendar.DATE, 2)
    val date = gc.time.time

    return SimpleDateFormat("EEEE", Locale.ENGLISH).format(date)
}
