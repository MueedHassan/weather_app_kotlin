package com.example.myapplication.model

import com.google.gson.annotations.SerializedName
import myapplication.Weather
import myapplication.Wind


data class WeatherList (

  @SerializedName("dt"         ) var dt         : Float?               = null,
  @SerializedName("main"       ) var main       : Main?              = Main(),
  @SerializedName("weather"    ) var weather    : ArrayList<Weather> = arrayListOf(),
  @SerializedName("clouds"     ) var clouds     : Clouds?            = Clouds(),
  @SerializedName("wind"       ) var wind       : Wind?              = Wind(),
  @SerializedName("visibility" ) var visibility : Float?               = null,
  @SerializedName("pop"        ) var pop        : Float?               = null,
  @SerializedName("sys"        ) var sys        : Sys?               = Sys(),
  @SerializedName("dt_txt"     ) var dtTxt      : String?            = null

)