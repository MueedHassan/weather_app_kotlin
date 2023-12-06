package com.example.myapplication.ui.fragments


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.R.layout.single_rv_fragment_1
import com.example.myapplication.model.WeatherList

class WeatherAdapter(private val weatherDataList: List<WeatherList>, private val context: Context) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(single_rv_fragment_1, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentWeather = weatherDataList[position]
        holder.bind(currentWeather)
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    class WeatherViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherData: WeatherList) {
            println("recyclerview $weatherData")
            // Bind other data properties to respective TextViews or views
            var a=weatherData.weather.toString().split(",")
            var weatherCondition=a[1].toString().split("=")
            var weatherIcon=a[3].toString().split("=")
            itemView.findViewById<TextView>(R.id.tv_recyclerview_fragment_1).text = " Tempreture ${weatherData.main?.temp.toString()}Calvin \n Description ${weatherCondition[1]}"
            itemView.findViewById<TextView>(R.id.tv_recyclerview2_fragment_1).text ="Feels like ${weatherData.main?.feelsLike} Calvin Clouds ${weatherData?.clouds?.all}   wind speed ${weatherData.wind?.speed}  \nDate and time ${weatherData?.dtTxt}"
            var imageHeader = itemView.findViewById<ImageView>(R.id.header_image)

            // Replace with your vector drawable resource ID
            val drawableId=findDrawable(weatherCondition[1])
            // Retrieve the vector drawable
            val drawable = AppCompatResources.getDrawable(itemView.context, drawableId)
            // Set the drawable to the ImageView
           imageHeader.setImageDrawable(drawable)
        }
    }
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
