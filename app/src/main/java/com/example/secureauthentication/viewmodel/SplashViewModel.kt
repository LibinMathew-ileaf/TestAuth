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
class SplashViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {
    val  isUserRegistered=dataStoreRepository.isUserRegistered()
}
