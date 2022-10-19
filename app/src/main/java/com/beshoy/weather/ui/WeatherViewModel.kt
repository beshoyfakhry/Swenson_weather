package com.beshoy.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beshoy.weather.data.remote.responses.response.CityNameResponse
import com.beshoy.weather.data.remote.responses.response.WeatherResponseObject
import com.beshoy.weather.other.Event
import com.beshoy.weather.other.Resource
import com.beshoy.weather.repositories.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    private val repository: WeatherRepository
) : ViewModel() {


    private val _weatherData = MutableLiveData<Event<Resource<WeatherResponseObject>>>()
    val weatherData: LiveData<Event<Resource<WeatherResponseObject>>> = _weatherData

    private val _cityNamesData = MutableLiveData<Event<Resource<CityNameResponse>>>()
    val cityNamesData: LiveData<Event<Resource<CityNameResponse>>> = _cityNamesData



    fun getForecast(cityName: String) {

        _weatherData.value = Event(Resource.loading(null))
        viewModelScope.launch {
            _weatherData.value = Event(repository.getForecast(cityName))
        }
    }

    fun getCityName(cityNameSubString:String)
    {
        _cityNamesData.value = Event(Resource.loading(null))
        viewModelScope.launch {
            _cityNamesData.value = Event(repository.getCitiesNamesList(cityNameSubString))
        }
    }
}













