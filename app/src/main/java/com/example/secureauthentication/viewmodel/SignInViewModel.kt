package com.example.secureauthentication.viewmodel

import androidx.lifecycle.LiveData
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

     private val _onSignInUserSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }
    val onSignInUserSuccess: LiveData<Boolean> get() = _onSignInUserSuccess

      private val _onSignInUserError: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }
     val  onSignInUserError: LiveData<String> get() = _onSignInUserError


    fun  signInUser( userName:String,password:String) {
        viewModelScope.launch {
            dataStoreRepository.signInUser(userName,password).collect { its ->
                when (its) {
                    is State.Failed -> {
                        its.exception.printStackTrace()
                        _onSignInUserSuccess.value = false
                    }
                    is State.Success -> {
                        _onSignInUserSuccess.value = true
                    }
                    is State.Error -> {
                        _onSignInUserError.value=its.error
                    }
                }
            }
        }
    }
}
