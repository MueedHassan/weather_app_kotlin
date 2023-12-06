package com.example.myapplication.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.datarepository.MyDataRepository
import com.example.myapplication.model.ForeCast
import com.example.myapplication.model.WeatherList
import com.example.myapplication.networkService.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyDataVM(private var repository: MyDataRepository) : ViewModel() {

    val myDataList:  MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val todayweatherList= (mutableListOf <WeatherList>())
    val forecastweatherList= (mutableListOf <WeatherList>())
    val currentWeatherAllValueList= (mutableListOf <WeatherList>())
    var currentdatetime=LocalDateTime.now()


    var currentdateformat=currentdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    var presentdate=currentdateformat



    fun getDataList() = viewModelScope.launch {
        myDataList.value = ApiState.Loading
        repository.getDataList()
            .catch { e ->
                myDataList.value = ApiState.Failure(e)
            }.collect { data ->
                myDataList.value = ApiState.Success(data)
            }
    }
    fun presentValue(data:ForeCast):WeatherList?{
        data.weatherList?.forEach {weather->
            if( weather.dtTxt!!.split("\\s".toRegex()).contains(presentdate)){
                todayweatherList.add(weather)

            }
        }
        val presentWeatherList: WeatherList?= findclosestwaether(todayweatherList)

        return presentWeatherList

    }
    fun currentDayAllValue(data:List<WeatherList>): MutableList<WeatherList> {
        for(weather in data){
            if(weather.dtTxt!!.split("\\s".toRegex()).contains(presentdate)){

                    currentWeatherAllValueList.add(weather)

            }

        }
        val wholeDayList: MutableList<WeatherList> = currentWeatherAllValueList

        return  wholeDayList

    }
    fun futureValue(data:List<WeatherList>): MutableList<WeatherList> {
          for(weather in data){
              if(!weather.dtTxt!!.split("\\s".toRegex()).contains(presentdate)){
                  if(weather.dtTxt!!.toString().substring(11,16)=="12:00")
                    forecastweatherList.add(weather)

              }

     }
        val forecastList: MutableList<WeatherList> =forecastweatherList
        return forecastList
    }

    fun findDrawable(dValue:String):Int{
        var drawableId = 0
        println("davalue: $dValue")
        if(dValue=="Clear")
        {
            drawableId = R.drawable.sunny_24
        }
        else if(dValue=="Clouds")
        {
             drawableId = R.drawable.baseline_cloud_24
        }
        else if(dValue=="Snow")
        {
            drawableId = R.drawable.round_hub_24
        }
        else if(dValue=="Rain")
        {
             drawableId = R.drawable.baseline_bolt_24
        }
        else
        {
            drawableId = R.drawable.sunny_24

        }



        return  drawableId
    }





}

private fun findclosestwaether(weatherList: List<WeatherList>): WeatherList? {
    val system= LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
    var closestWeather:WeatherList?=null
    var minTimeDifference=Int.MAX_VALUE
    for(weather in weatherList){
        val weatherTime=weather.dtTxt!!.substring(11,16)
        val timeDifference=Math.abs(timeToMinute(weatherTime)-timeToMinute(system))
        if(timeDifference<minTimeDifference)
        {
            minTimeDifference=timeDifference
            closestWeather=weather
        }

    }
    return closestWeather
}

fun timeToMinute(time: String?): Int {
    val parts=time?.split(":")
    return parts?.get(0)!!.toInt() *60 + parts?.get(1)!!.toInt()
}



