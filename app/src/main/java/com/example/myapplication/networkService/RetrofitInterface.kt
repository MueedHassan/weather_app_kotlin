package com.example.myapplication.networkService

import com.example.myapplication.model.ForeCast
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitInterface {

    @GET("forecast?")
    suspend fun getDataList(
        @Query("lat")
        lat:String="44.34",
        @Query("lon")
        lon:String="10.99",
        @Query("appid")
        appid:String="3a22d9e823d82314c95342661363cbc9",

        ): ForeCast

}
