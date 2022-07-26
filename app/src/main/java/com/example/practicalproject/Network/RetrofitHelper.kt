package com.example.practicalproject.Network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitHelper {
    private val Baseurl="https://demo3231717.mockable.io/"
    var gson = GsonBuilder()
        .setLenient()
        .create()

   var httpClient = OkHttpClient.Builder()

    val retrofit = Retrofit.Builder()
        .baseUrl(Baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



}