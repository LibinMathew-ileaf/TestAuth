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
class MainActivityViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

     val onFetchDetails: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }


    fun  getUserDetail() {
        viewModelScope.launch {
            dataStoreRepository.getUserDetails().collect { its ->
                when (its) {
                    is State.Failed -> {
                        its.exception.printStackTrace()
                    }
                    is State.Success -> {
                        onFetchDetails.value = its.data
                    }
                    is State.Error -> {

                    }
                }
            }
        }
    }
}
