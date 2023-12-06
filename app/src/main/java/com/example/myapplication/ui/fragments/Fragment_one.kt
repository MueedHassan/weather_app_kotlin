package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datarepository.MyDataRepository
import com.example.myapplication.model.ForeCast
import com.example.myapplication.model.WeatherList
import com.example.myapplication.mvvm.MyDataVM
import com.example.myapplication.mvvm.MyDataViewModelFactory
import com.example.myapplication.networkService.ApiState
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Fragment_one : Fragment() {

     private lateinit var myDataVM: MyDataVM

    val systemTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
    var currentdatetime=LocalDateTime.now()
    var currentdateformat=currentdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_one, container, false)


        myDataVM = ViewModelProvider(
            this,
            MyDataViewModelFactory(MyDataRepository())
        )[MyDataVM::class.java]
        myDataVM.getDataList()
        lifecycleScope.launch {
            myDataVM.myDataList.collect {
                    apiState ->
                when (apiState) {
                    is ApiState.Loading -> {
                       println("value is loading")
                    }
                    is ApiState.Success ->
                    {
                        val data: ForeCast = apiState.data
                        val currentWeather: WeatherList?= myDataVM.presentValue(data)
                        // Find the weather data closest to the current time

                        println("recent present weather :$currentWeather")

                        //declaraction
                        val  locationTextView:TextView=view.findViewById(R.id.tv_Location_Time_Date)
                        val  temperatureTextView: TextView = view.findViewById(R.id.tv_temp)
                        val  feelsLikeTextView:TextView=view.findViewById(R.id.tv_feels_like)
                        val  bodyTextView:TextView=view.findViewById(R.id.tv_body)
                        val  image_Header: ImageView =view.findViewById(R.id.header_image)
                        var a=currentWeather?.weather.toString().split(",")
                        var weatherCondition=a[1].toString().split("=")
                        var weatherIcon=a[3].toString().split("=")
                        locationTextView.text=("City: ${data.city?.name} \n Time:${systemTime}\n Date:${currentdateformat}")


                         // Replace with your vector drawable resource ID
                        val drawableId=myDataVM.findDrawable(weatherCondition[1])
                        // Retrieve the vector drawable
                        val drawable = AppCompatResources.getDrawable(requireContext(), drawableId)
                        // Set the drawable to the ImageView
                        image_Header.setImageDrawable(drawable)
                        temperatureTextView.text = "Temperature: ${currentWeather?.main?.temp} Calvin ,${weatherCondition[1]}"
                        feelsLikeTextView.text = "Feels Like: ${currentWeather?.main?.feelsLike} Calvin \n  "
                        bodyTextView.text = "Clouds: ${currentWeather?.clouds?.all}  \n wind Speed:${currentWeather?.wind?.speed} "
                        // Assuming you have received the weatherDataList from Retrofit
                        //recycler view
                        var currentDayAllValue=myDataVM.currentDayAllValue(data.weatherList)
                        val weatherDataList: List<WeatherList> =currentDayAllValue
                        println("Recyclerview1 ${weatherDataList}")
                        val currentAllValueRecyclerView: RecyclerView = view.findViewById(R.id.recycler_view) // Replace with your RecyclerView ID
                        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false) // or LinearLayoutManager(requireContext()) in Fragment
                        currentAllValueRecyclerView.layoutManager = layoutManager

                        val adapter = WeatherAdapter(weatherDataList,requireContext())
                        currentAllValueRecyclerView.adapter = adapter



                    }
                    is ApiState.Failure -> {
                        val errorMessage = apiState.e.message
                        // Handle failure, show error message, etc.
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()


                    }
                    else -> {
                        // Handle other states if needed
                    }
                }
                }
            }

        return view
    }

    }



