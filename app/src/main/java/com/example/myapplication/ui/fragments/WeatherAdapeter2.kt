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
import com.example.myapplication.model.WeatherList
class WeatherAdapter2(private val weatherDataList: List<WeatherList>,private val context: Context) :
    RecyclerView.Adapter<WeatherAdapter2.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_view, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentWeather = weatherDataList[position]
        holder.bind(currentWeather)
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherData: WeatherList) {
            // Bind data to views within the item layout
            var a=weatherData.weather.toString().split(",")
            var weatherCondition=a[1].toString().split("=")
            var weatherIcon=a[3].toString().split("=")
            itemView.findViewById<TextView>(R.id.tv_recyclerview).text = " Tempreture ${weatherData.main?.temp.toString()}Calvin \n Description ${weatherCondition[1]}"
            itemView.findViewById<TextView>(R.id.tv_recyclerview2).text ="Feels like ${weatherData.main?.feelsLike} Calvin\nClouds ${weatherData?.clouds?.all}\n wind speed ${weatherData.wind?.speed}\n Date and time ${weatherData?.dtTxt}"

            var imageHeader = itemView.findViewById<ImageView>(R.id.header_image_fragment_2)

            // Replace with your vector drawable resource ID
            val drawableId=findDrawable(weatherCondition[1])
            // Retrieve the vector drawable
            val drawable = AppCompatResources.getDrawable(itemView.context, drawableId)
            // Set the drawable to the ImageView
            imageHeader.setImageDrawable(drawable)


        }
    }
}
