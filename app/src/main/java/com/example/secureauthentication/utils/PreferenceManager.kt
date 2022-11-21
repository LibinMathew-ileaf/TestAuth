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

}