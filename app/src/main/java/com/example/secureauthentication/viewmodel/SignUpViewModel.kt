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
class SignUpViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

    private val _onSaveUserData: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }
    val onSaveUserData: LiveData<Boolean> get() = _onSaveUserData
    fun saveUserData(name :String,userName:String,password:String) {
        viewModelScope.launch {
            dataStoreRepository.saveUserData(name,userName,password).collect { its ->
                when (its) {
                    is State.Failed -> {
                        _onSaveUserData.value=false
                    }
                    is State.Success -> {
                        _onSaveUserData.value=true
                    }
                    is State.Error -> {

                    }
                }
            }
        }
    }
}
