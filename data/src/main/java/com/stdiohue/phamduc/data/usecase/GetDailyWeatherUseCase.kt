package com.stdiohue.phamduc.data.usecase

import com.google.gson.Gson
import com.stdiohue.phamduc.data.response.DailyWeatherResponse
import com.stdiohue.phamduc.network.services.WeatherServices
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GetDailyWeatherUseCase(var gson: Gson, var weatherServices: WeatherServices) {
    fun execute(lat: Double, lng: Double): Observable<DailyWeatherResponse> {
        return weatherServices.getDailyWeather(lat, lng)
                .subscribeOn(Schedulers.io())
                .map { response -> gson.fromJson(response, DailyWeatherResponse::class.java) }
    }
}