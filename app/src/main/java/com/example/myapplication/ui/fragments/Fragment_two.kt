package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class Fragment_two : Fragment() {
    private lateinit var myDataVM: MyDataVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_two, container, false)
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
                    {   val data: ForeCast = apiState.data

                        val futureWeather: MutableList<WeatherList> =myDataVM.futureValue(data.weatherList)
                        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view2) // Replace with your RecyclerView ID
                        val layoutManager = LinearLayoutManager(requireContext()) // or LinearLayoutManager(requireContext()) in Fragment
                        recyclerView.layoutManager = layoutManager

                        val adapter = WeatherAdapter2(futureWeather,requireContext())
                        recyclerView.adapter = adapter

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