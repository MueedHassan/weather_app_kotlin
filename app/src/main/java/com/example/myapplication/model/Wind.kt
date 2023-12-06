package myapplication

import com.google.gson.annotations.SerializedName


data class Wind (

  @SerializedName("speed" ) var speed : Double? = null,
  @SerializedName("deg"   ) var deg   : Float?    = null,
  @SerializedName("gust"  ) var gust  : Double? = null

)