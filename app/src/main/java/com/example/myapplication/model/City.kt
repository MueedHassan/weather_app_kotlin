package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


data class City (

  @SerializedName("id"         ) var id         : Float?    = null,
  @SerializedName("name"       ) var name       : String? = null,
  @SerializedName("coord"      ) var coord      : Coord?  = Coord(),
  @SerializedName("country"    ) var country    : String? = null,
  @SerializedName("population" ) var population : Float?    = null,
  @SerializedName("timezone"   ) var timezone   : Float?    = null,
  @SerializedName("sunrise"    ) var sunrise    : Float?    = null,
  @SerializedName("sunset"     ) var sunset     : Float?    = null

)