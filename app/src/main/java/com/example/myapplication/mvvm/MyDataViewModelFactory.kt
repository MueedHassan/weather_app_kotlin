package com.example.myapplication.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.datarepository.MyDataRepository

class MyDataViewModelFactory(private val repository: MyDataRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyDataVM(repository) as T
    }
}