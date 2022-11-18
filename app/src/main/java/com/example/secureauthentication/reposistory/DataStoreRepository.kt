package com.example.secureauthentication.reposistory


import android.util.Base64
import android.util.Log
import com.example.secureauthentication.utils.CryptoManger
import com.example.secureauthentication.utils.PreferenceManager
import com.example.secureauthentication.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataStoreRepository @Inject constructor(private val pref: PreferenceManager,
                                              private val cryptoManger: CryptoManger) {

    fun saveUserData(name: String, userName: String, password: String): Flow<State<Boolean>> {
        return flow {
            try {
                val enCodeUserName = encryptData(userName)
               //Base64.encodeToString(enCodeUserName, Base64.DEFAULT);
                 var en=   cryptoManger.encrypt(userName.encodeToByteArray())
                 cryptoManger.decrypt(en)

//                Base64.decode(enCodeUserName, Base64.DEFAULT)
                pref.setUserName(enCodeUserName)
                val enCodePassword = encryptData(password)
                pref.setPassword(enCodePassword)
                pref.setName(name)
                Log.d("Pass", pref.getPassword())
                Log.d("Pass", pref.getName())
                Log.d("Pass", pref.getUserName())
                emit(State.Success(true))
            } catch (e: Exception) {
                emit(State.Failed(e.localizedMessage!!))
            }

        }.flowOn(Dispatchers.IO)
    }
    fun fromHexString(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }
    fun isUserRegistered(): Boolean {
        if (pref.getName().isNotEmpty()) {
            return true
        }

        return false
    }

    private fun encryptData(data: String): String {
        val bytes = data.encodeToByteArray()

      return  Base64.encodeToString(cryptoManger.encrypt(
            bytes = bytes,
        ), Base64.DEFAULT)
    }

    fun signInUser( userName: String, password: String): Flow<State<Boolean>> {
        return flow {
            try {
                cryptoManger.decrypt(pref.getUserName().encodeToByteArray())
                val enCodePassword = encryptData(password)
                pref.setPassword(enCodePassword)
                Log.d("Pass", pref.getPassword())
                Log.d("Pass", pref.getName())
                Log.d("Pass", pref.getUserName())
                emit(State.Success(true))
            } catch (e: Exception) {
                emit(State.Failed(e.localizedMessage!!))
            }
        }
    }
}


