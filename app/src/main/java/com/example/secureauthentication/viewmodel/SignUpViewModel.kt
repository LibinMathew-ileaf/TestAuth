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
class SignUpViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

    val onSaveUserData: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    fun saveUserData(name :String,userName:String,password:String) {
        viewModelScope.launch {
            dataStoreRepository.saveUserData(name,userName,password).collect { its ->
                when (its) {
                    is State.Failed -> {
                        onSaveUserData.value=false
                    }
                    is State.Success -> {
                        onSaveUserData.value=true
                    }
                    is State.Error -> {

                    }
                }
            }
        }
    }
}
