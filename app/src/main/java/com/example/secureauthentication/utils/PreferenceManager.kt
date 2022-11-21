package com.example.secureauthentication.utils

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager @Inject constructor(private val preferences: SharedPreferences) {
    companion object {
        const val USER_DETAIL = "user_detail"
        const val USER_DETAIL_IV = "user_detail_iv"
    }

    private fun setPreferenceStringData(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun clearAll() {
        val editor = preferences.edit()
        editor.clear()
        editor.commit()
    }

    fun setUserDetail(userDetail:String){
        setPreferenceStringData(USER_DETAIL,userDetail)
    }
    fun setUserDetailIv(iv:String){
        setPreferenceStringData(USER_DETAIL_IV,iv)
    }
    fun getUserDetail() = preferences.getString(USER_DETAIL, "")!!
    fun getUserDetailIv() = preferences.getString(USER_DETAIL_IV, "")!!
}