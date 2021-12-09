package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import retrofit2.Response

class ActivityViewModel : BaseViewModel() {
    val simpleLiveData = MutableLiveData<Event<Response<WeatherResponse>>>()
    var result : WeatherResponse? = null
    // Получение юзеров. Обращаемся к функции  requestWithLiveData
    // из BaseViewModel передаем нашу лайвдату и говорим,
    // какой сетевой запрос нужно выполнить и с какими параметрами
    // В данном случае это api.getUsers
    // Теперь функция сама выполнит запрос и засетит нужные
    // данные в лайвдату
    fun getWeatherOfCity(q: String)  {
        return requestWithLiveData(simpleLiveData) {
            apiService!!.getWeatherOfCity(
                q = q
            )
        }

    }
}