package ru.easycode.zerotoheroandroidtdd.data

import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL = "https://google.com/"

    fun getInstance(): retrofit2.Retrofit {
        return retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}