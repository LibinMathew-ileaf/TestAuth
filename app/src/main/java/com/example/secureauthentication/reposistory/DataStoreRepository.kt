package com.example.secureauthentication.reposistory


import android.app.Application
import android.util.Base64
import com.example.secureauthentication.R
import com.example.secureauthentication.utils.*
import com.example.secureauthentication.utils.Decryptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataStoreRepository @Inject constructor(private val pref: PreferenceManager,
                                              private val applicationContext: Application, private val encryptor: Encryptor, private val decryptor: Decryptor) {

    fun saveUserData(name: String, userName: String, password: String): Flow<State<Boolean>> {
        return flow {
            try {
                val userDetail= "$name/$userName/$password"
                val (encryptUserDetail,iv) = encryptString(userDetail)
                pref.setUserDetail(encryptUserDetail)
                pref.setUserDetailIv(iv)
                return@flow emit(State.Success(true))
            } catch (e: Exception) {
                println(e.stackTrace)
               return@flow emit(State.Failed(e))
            }

        }.flowOn(Dispatchers.IO)
    }

    private fun decryptString (encryptedString:String,iv: String):String {
        return  decryptor.decryptData(
            applicationContext.getString(R.string.alias),
            Base64.decode(encryptedString, Base64.DEFAULT),
            Base64.decode(iv, Base64.DEFAULT)
        )

    }

    fun isUserRegistered(): Boolean {
        if (pref.getUserDetail().isNotEmpty()) {
            return true
        }

        return false
    }

    private fun encryptString(userName: String): Pair<String,String> {
        val byteArray = encryptor.encryptText(applicationContext.getString(R.string.alias), userName)
        return  Pair(Base64.encodeToString(byteArray, Base64.DEFAULT),Base64.encodeToString(encryptor.iv, Base64.DEFAULT))
    }


    fun signInUser( userName: String, password: String): Flow<State<Boolean>> {
        return flow {
            try {
                val decryptedUserDetail = decryptString(pref.getUserDetail(),pref.getUserDetailIv())

                val split = decryptedUserDetail.split('/')
                //var decryptedPassword= decryptString(pref.getPassword(),pref.getPasswordIV())

                if(split[1]!=userName){
                    return@flow emit(State.Error("Invalid UserName"))
                }
                if(split[2]!=password){
                    return@flow emit(State.Error("Incorrect Password"))
                }
                emit(State.Success(true))
            } catch (e: Exception) {
                emit(State.Failed(e))
            }
        }
    }

    fun getUserDetails():  Flow<State<String>> {
        return flow {
            try {
                val decryptedUserDetail = decryptString(pref.getUserDetail(), pref.getUserDetailIv())
                return@flow emit(State.Success(decryptedUserDetail))


            } catch (e: Exception) {
                emit(State.Failed(e))
            }
        }
    }
}


