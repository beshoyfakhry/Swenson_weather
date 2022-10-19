package com.beshoy.weather.other

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


fun AppCompatActivity.getCurrentTime():String
{
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
   return formatter.format(time)
}
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
