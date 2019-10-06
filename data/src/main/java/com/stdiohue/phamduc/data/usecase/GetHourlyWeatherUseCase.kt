package com.stdiohue.phamduc.data.usecase

import com.google.gson.Gson
import com.stdiohue.phamduc.data.response.HourlyWeatherResponse
import com.stdiohue.phamduc.network.services.WeatherServices
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GetHourlyWeatherUseCase(var gson: Gson, var weatherServices: WeatherServices) {
    fun execute(lat: Double, lng: Double): Observable<HourlyWeatherResponse> {
        return weatherServices.getHourlyWeather(lat, lng)
                .subscribeOn(Schedulers.io())
                .map { response -> gson.fromJson(response, HourlyWeatherResponse::class.java) }
    }
}