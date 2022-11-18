package com.example.secureauthentication.utils

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastHelper @Inject constructor(private val context: Context) {
    fun showToast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}