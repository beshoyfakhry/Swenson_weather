package com.beshoy.weather.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.beshoy.weather.R
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: WeatherFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
//        actionBar?.hide()

        val actionBar: ActionBar? = supportActionBar
        // Set below attributes to add logo in ActionBar.
        // Set below attributes to add logo in ActionBar.
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayUseLogoEnabled(true)

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("HH:mm")
        val current = formatter.format(time)

        actionBar.title = current
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }


}