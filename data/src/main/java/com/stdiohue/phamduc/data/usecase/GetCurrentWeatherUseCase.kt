package com.stdiohue.phamduc.data.usecase

import com.google.gson.Gson
import com.stdiohue.phamduc.data.response.CurrentWeatherResponse
import com.stdiohue.phamduc.network.services.WeatherServices
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GetCurrentWeatherUseCase(var gson: Gson, var weatherServices: WeatherServices) {
    fun execute(lat: Double, lng: Double): Observable<CurrentWeatherResponse> {
        return weatherServices.getCurrentWeather(lat, lng)
                .subscribeOn(Schedulers.io())
                .map { response -> gson.fromJson(response, CurrentWeatherResponse::class.java) }
    }
}