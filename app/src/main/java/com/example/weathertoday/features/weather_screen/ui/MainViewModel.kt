package com.example.weathertoday.features.weather_screen.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.weathertoday.base.BaseViewModel
import com.example.weathertoday.base.Event
import com.example.weathertoday.features.weather_screen.domain.WeatherIteractor
import kotlinx.coroutines.launch

class MainViewModel(val iteractor: WeatherIteractor) : BaseViewModel<ViewState>() {

    // Переопределение функции initialViewState(), которая возвращает экземпляр класса ViewState со значениями по умолчанию
    override fun initialViewState(): ViewState =
        ViewState(
            temp = 0f,
            speed = 0f,
            tempMax = 0f,
            tempMin = 0f,
            feelsLike = 0f,
            pressure = 0,
            city = "",
            humidity = 0,
            sunset = 0,
            sunrise = 0,
            date = 0,
            isLoading = false,
            timeZone = 0,
        )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            // Внутри функции происходит проверка типа event, если это UiEvent.LoadWeatherFromCity, то значение поля city в объекте previousState устанавливается в значение event.city.
            is UiEvent.LoadWeatherFromCity -> {
                previousState.city = event.city

                // Затем вызывается метод getWeather интерактора с параметром event.city внутри viewModelScope.launch.
                viewModelScope.launch {
                    iteractor.getWeather(event.city).fold(
                        onError = {
                            Log.e("Error", it.localizedMessage)
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnWeatherSucceed(it))
                        }
                    )
                }
                return null
            }

            // Если тип event равен DataEvent.OnWeatherSucceed, то возвращает новый экземпляр ViewState с обновленными полями, которые присваиваются значениям, полученным из event.data.
            is DataEvent.OnWeatherSucceed -> {
                return previousState.copy(
                    temp = event.data.main.temp,
                    speed = event.data.wind.speed,
                    tempMax = event.data.main.tempMax,
                    tempMin = event.data.main.tempMin,
                    feelsLike = event.data.main.feelsLike,
                    pressure = event.data.main.pressure,
                    humidity = event.data.main.humidity,
                    sunset = event.data.sys.sunset,
                    sunrise = event.data.sys.sunrise,
                    date = event.data.date,
                    isLoading = true,
                    timeZone = event.data.timeZone,
                )
            }
            else -> return null

        }
    }
}
