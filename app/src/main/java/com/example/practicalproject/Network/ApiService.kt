package com.example.practicalproject.Network


import com.example.practicalproject.modelclass.Coins
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {


    @GET("/coinlist")
    suspend fun getcoins():Response<Coins>








}