package com.example.secureauthentication.utils;

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager @Inject constructor(private val preferences: SharedPreferences) {
    companion object {
        const val NAME = "name"
        const val USER_NAME = "user_name"
        const val PASSWORD = "password"

    }

    private fun setPreferenceStringData (key:String, value:String){
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun clearAll () {
        val editor = preferences.edit()
        editor.clear()
        editor.commit()
    }

    fun getUserName () = preferences.getString(USER_NAME, "")!!
    fun setUserName (userName:String){
        setPreferenceStringData(USER_NAME, userName)
    }
    fun getName () = preferences.getString(NAME, "")!!
    fun setName (userName:String){
        setPreferenceStringData(NAME, userName)
    }
    fun getPassword () = preferences.getString(PASSWORD, "")!!
    fun setPassword ( password:String){
        setPreferenceStringData(PASSWORD, password)
    }



}