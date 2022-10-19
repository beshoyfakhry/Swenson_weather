package com.beshoy.weather.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class WeatherFragmentFactory @Inject constructor(
    private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            WeatherHomeFragment::class.java.name -> WeatherHomeFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}