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
class MainActivityViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

     private val _onFetchDetails: MutableLiveData<String> by lazy {
        MutableLiveData("")
     }
    val onFetchDetails: LiveData<String> get() = _onFetchDetails

    fun  getUserDetail() {
        viewModelScope.launch {
            dataStoreRepository.getUserDetails().collect { its ->
                when (its) {
                    is State.Failed -> {
                        its.exception.printStackTrace()
                    }
                    is State.Success -> {
                        _onFetchDetails.value = its.data
                    }
                    is State.Error -> {

                    }
                }
            }
        }
    }
}
