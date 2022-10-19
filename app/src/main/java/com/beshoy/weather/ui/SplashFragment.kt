package com.beshoy.weather.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.beshoy.weather.R
import com.beshoy.weather.other.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment(
) : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        lifecycleScope.launch {
            delay(Constants.SPLASH_TIME_DELAY)
            startNextView()
        }

    }

    private fun startNextView() {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToWeatherHomeFragment()
        )
    }
}
















