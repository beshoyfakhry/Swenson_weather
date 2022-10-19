package com.beshoy.weather.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beshoy.weather.R
import com.beshoy.weather.data.remote.responses.objects.Forecast
import com.beshoy.weather.data.remote.responses.response.CityNameResponse
import com.beshoy.weather.other.Status
import com.beshoy.weather.other.dayAfterTomorrow
import com.beshoy.weather.other.today
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather_home.*
import javax.inject.Inject


@AndroidEntryPoint
class WeatherHomeFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment(R.layout.fragment_weather_home) {

    lateinit var viewModel: WeatherViewModel

    lateinit var searchAutoComplete: SearchView.SearchAutoComplete

    //Default City
    var selectedCity = "London"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)

        getTheWeatherData()
        subscribeToObservers()

    }

    private fun subscribeToObservers() {
        viewModel.weatherData.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        main_view.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        setData(result.data!!.forecast)
                    }
                    Status.ERROR -> {
                        Snackbar.make(
                            requireActivity().rootLayout,
                            result.message ?: "An unknown error occured.",
                            Snackbar.LENGTH_LONG
                        ).show()
                        progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        main_view.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
        viewModel.cityNamesData.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        setCitiesNames(result.data)
                    }
                    Status.ERROR -> {
                        Snackbar.make(
                            requireActivity().rootLayout,
                            result.message ?: "An unknown error occured.",
                            Snackbar.LENGTH_LONG
                        ).show()
                        progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setCitiesNames(data: CityNameResponse?) {
        val citiesNames = ArrayList<String>()
        data!!.forEach {
            citiesNames.add(
                it.toString()
            )
        }

        val citiesNamesAdapter =
            activity?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    citiesNames
                )
            }

//        cityNamesAdapter.citiesNames = citiesNames
        //  searchAutoComplete.setAdapter(cityNamesAdapter)
        searchAutoComplete.setAdapter(citiesNamesAdapter)

    }

    private fun getTheWeatherData() {

        viewModel.getForecast(selectedCity)
    }

    private fun getCitiesNamesList(cityNameSubString: String) {
        viewModel.getCityName(cityNameSubString)
    }

    private fun setData(forecast: Forecast) {

        tv_city_name.text = selectedCity
        tv_today_date.text = today()

        glide
            .load(forecast.forecastList[0].day.condition.getIconLink())
            .centerCrop()
            .into(iv_condition_image)

        tv_today_temp.text = forecast.forecastList[0].day.getAvgTempF()
        tv_condition_desc.text = forecast.forecastList[0].day.condition.text
        tv_today_wind.text = forecast.forecastList[0].day.getWindSpeed()
        tv_today_humidity.text = forecast.forecastList[0].day.getHumidity()

        glide
            .load(forecast.forecastList[0].day.condition.getIconLink())
            .centerCrop()
            .into(iv_forecast_today_condition)

        glide
            .load(forecast.forecastList[1].day.condition.getIconLink())
            .centerCrop()
            .into(iv_forecast_tomorrow_condition)

        glide
            .load(forecast.forecastList[2].day.condition.getIconLink())
            .centerCrop()
            .into(iv_forecast_after_tomorrow_condition)

        tv_temp_today.text = forecast.forecastList[0].day.getTheDayMinAndMaxTemp()
        tv_temp_tomorrow.text = forecast.forecastList[1].day.getTheDayMinAndMaxTemp()
        tv_temp_after_tomorrow.text = forecast.forecastList[2].day.getTheDayMinAndMaxTemp()


        tv_week_day_after_tomorrow.text = dayAfterTomorrow()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        // Inflate the search menu action bar.
        inflater.inflate(R.menu.action_bar_search_example_menu, menu)

        val searchMenu: MenuItem = menu.findItem(R.id.app_bar_menu_search)


        // Get SearchView object.
        val searchView = MenuItemCompat.getActionView(searchMenu) as SearchView

        searchView.setBackgroundResource(R.drawable.round)

        // Get SearchView autocomplete object.

        searchAutoComplete =
            searchView.findViewById(R.id.search_src_text) as SearchView.SearchAutoComplete
        searchAutoComplete.setTextColor(Color.BLACK)
        searchAutoComplete.hint = "Search City"


        searchAutoComplete.addTextChangedListener {
            if (it!!.isNotEmpty() && it.length > 2) {
                getCitiesNamesList(it.toString())
            }
            // Listen to search view item on click event.
            searchAutoComplete.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, itemIndex, id ->
                    searchMenu.collapseActionView()
                    val queryString = adapterView.getItemAtPosition(itemIndex) as String
                    selectedCity = queryString.split("-")[0].trim()
                    getTheWeatherData()

                }

            super.onCreateOptionsMenu(menu, inflater)

        }

    }
}