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

    private val onSignInUser: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    fun  signInUser( userName:String,password:String) {
        viewModelScope.launch {
            dataStoreRepository.signInUser(userName,password).collect { its ->
                when (its) {
                    is State.Failed -> {
                        onSignInUser.value=false
                    }
                    is State.Success -> {
                        onSignInUser.value=true
                    }

                    else -> {

                    }
                }
            }
        }
    }
}
