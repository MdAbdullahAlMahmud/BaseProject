package com.example.baseprojectsetup.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.example.baseprojectsetup.data.model.request.LoginRequest
import com.example.baseprojectsetup.data.model.response.LoginResponse
import com.example.baseprojectsetup.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private  val authRepository: AuthRepository
) : BaseViewModel(){

    private val _loginResponse = MutableLiveData<SingleLiveEvent<LoginResponse>>()
    val loginResponse : LiveData<SingleLiveEvent<LoginResponse>> = _loginResponse

    private val _logOutResponse = MutableLiveData<SingleLiveEvent<Boolean>>()
    val logOutResponse : LiveData<SingleLiveEvent<Boolean>> = _logOutResponse


    fun  getLogOut(){
        viewModelScope.launch {
            val response = callService { authRepository.logOut() }
            response?.data?.let {
                _logOutResponse.value = SingleLiveEvent(it)
            }
        }
    }

    fun requestLogin(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = callService { authRepository.requestForLogin(loginRequest) }
            response?.data?.let {
                _loginResponse.value = SingleLiveEvent(it)
            }
        }
    }



}