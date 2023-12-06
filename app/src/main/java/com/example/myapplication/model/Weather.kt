package myapplication

import com.google.gson.annotations.SerializedName


data class Weather (

  @SerializedName("id"          ) var id          : Float?    = null,
  @SerializedName("main"        ) var main        : String? = null,
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("icon"        ) var icon        : String? = null

)