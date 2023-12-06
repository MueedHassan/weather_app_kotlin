package com.example.myapplication.networkService

object AllApi {

    private external fun baseUrlFromJNI(boolean: Boolean): String

    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    const val V1 = "7e55954e5977c056e46275c334b07040"

    const val PERMISSION_REQUEST_CODE=123

    const val DATA_LIST = "forecast?"
}
