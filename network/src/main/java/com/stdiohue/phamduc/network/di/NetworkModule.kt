package com.stdiohue.phamduc.network.di

import com.stdiohue.phamduc.network.BuildConfig
import com.stdiohue.phamduc.network.services.WeatherServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@Module
class NetworkModule {
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }

        httpClient.addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            try {
                val code = response.code()
                if (code < 200 || code > 299) {
                    if (code == 500) {
                        throw IOException("Internal Server Error.")
                    } else {
                        if (response.body() != null) {
                            val jsonObject = JSONObject(response.body()!!.string())
                            var messageError: String? = null
                            if (jsonObject.has("message")) {
                                messageError = jsonObject.getString("message")
                            }
                            throw IOException(messageError)
                        }
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            response
        }

        httpClient.addInterceptor(logging)
        return httpClient
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
    }

    @Provides
    fun provideWeatherServices(retrofit: Retrofit): WeatherServices {
        return retrofit.create(WeatherServices::class.java)
    }
}