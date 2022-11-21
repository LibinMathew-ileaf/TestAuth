package com.example.secureauthentication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secureauthentication.reposistory.DataStoreRepository
import com.example.secureauthentication.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

     val onSignInUserSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }
      val onSignInUserError: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }

    fun  signInUser( userName:String,password:String) {
        viewModelScope.launch {
            dataStoreRepository.signInUser(userName,password).collect { its ->
                when (its) {
                    is State.Failed -> {
                        its.exception.printStackTrace()
                        onSignInUserSuccess.value = false
                    }
                    is State.Success -> {
                        onSignInUserSuccess.value = true
                    }
                    is State.Error -> {
                        onSignInUserError.value=its.error
                    }
                }
            }
        }
    }
}
