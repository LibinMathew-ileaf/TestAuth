package com.example.secureauthentication.utils

sealed class State<T> {
    data class Error<T>(val error: String) : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Failed<T>(val message: String) : State<T>()
}