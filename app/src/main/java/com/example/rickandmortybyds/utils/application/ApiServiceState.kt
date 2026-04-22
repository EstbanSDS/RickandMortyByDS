package com.example.rickandmortybyds.utils.application

import com.example.rickandmortybyds.utils.application.ErrorApiResponse

sealed class ApiServiceState <out T>{
    object Loading : ApiServiceState<Nothing>()
    data class Success<out T>(val data: T?) : ApiServiceState<T>()
    data class Error(val message: ErrorApiResponse) : ApiServiceState<Nothing>()
}