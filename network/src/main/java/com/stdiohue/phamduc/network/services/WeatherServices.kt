package com.stdiohue.phamduc.network.services

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherServices {
    @GET("{lat},{lng}?exclude=hourly,daily,flags?units=si")
    fun getCurrentWeather(@Path(value = "lat", encoded = true) lat: Double, @Path(value = "lng", encoded = true) lng: Double): Observable<JsonObject>

    @GET("{lat},{lng}?exclude=currently,daily,flags")
    fun getHourlyWeather(@Path(value = "lat", encoded = true) lat: Double, @Path(value = "lng", encoded = true) lng: Double): Observable<JsonObject>

    @GET("{lat},{lng}?exclude=currently,hourly,flags")
    fun getDailyWeather(@Path(value = "lat", encoded = true) lat: Double, @Path(value = "lng", encoded = true) lng: Double): Observable<JsonObject>
}