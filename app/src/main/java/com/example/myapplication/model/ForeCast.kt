package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


data class ForeCast(

    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: Float? = null,
    @SerializedName("cnt") var cnt: Float? = null,
    @SerializedName("list") var weatherList: ArrayList<WeatherList> = arrayListOf(),
    @SerializedName("city") var city: City? = City()

)