package com.example.secureauthentication.viewmodel

import android.util.Log
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
class SplashViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

    private val _isUserRegistered: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }
    val isUserRegistered: LiveData<Boolean> get() = _isUserRegistered

    fun  checkUserRegistered() {
        viewModelScope.launch {
            dataStoreRepository.isUserRegistered().collect { its ->
                when (its) {
                    is State.Failed -> {
                        its.exception.printStackTrace()

                    }
                    is State.Success -> {
                        _isUserRegistered.value=true
                    }
                    is State.Error -> {
                        Log.d("Error",its.error)
                        _isUserRegistered.value=false
                    }
                }
            }
        }
    }
}
