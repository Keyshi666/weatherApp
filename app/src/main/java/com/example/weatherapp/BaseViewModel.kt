package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    var apiService: ApiService? = null
    fun <WeatherResponse> requestWithLiveData(
        liveData: MutableLiveData<Event<WeatherResponse>>,
        request: suspend () -> WeatherResponse) {

        // В начале запроса сразу отправляем ивент загрузки
        liveData.postValue(Event.loading())

        // Привязываемся к жизненному циклу ViewModel, используя viewModelScope.
        // После ее уничтожения все выполняющиеся длинные запросы
        // будут остановлены за ненадобностью.
        // Переходим в IO поток и стартуем запрос
        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                liveData.postValue(Event.success(response))

            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }

    fun <WeatherResponse> requestWithCallback(
        request: suspend () -> WeatherResponse,
        response: (Event<WeatherResponse>) -> Weather) {

        response(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = request.invoke()

                // здесь все аналогично, но полученные данные
                // сетим в колбек уже в главном потоке, чтобы
                // избежать конфликтов с
                // последующим использованием данных
                // в context классах
                launch(Dispatchers.Main) {
                        response(Event.success(res))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // UPD (подсказали в комментариях) В блоке catch ивент передаем тоже в Main потоке
                launch(Dispatchers.Main) {
                    response(Event.error(null))
                }
            }
        }
    }
}