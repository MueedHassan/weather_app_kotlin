package com.example.myapplication.networkService

import com.example.myapplication.model.ForeCast

sealed class ApiState {




    object Loading : ApiState()
    class Failure(val e: Throwable) : ApiState()
    class Success(val data: ForeCast) : ApiState()
    object Empty : ApiState()
}
