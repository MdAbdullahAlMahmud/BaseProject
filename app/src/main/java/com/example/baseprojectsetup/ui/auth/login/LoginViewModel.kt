package com.example.baseprojectsetup.ui.auth.login

import android.util.Log
import android.widget.EditText
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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : com.mkrlabs.common.core.base.BaseViewModel() {

    private val _loginResponse = MutableLiveData<com.mkrlabs.common.core.base.utils.SingleLiveEvent<LoginResponse>>()
    val loginResponse : LiveData<com.mkrlabs.common.core.base.utils.SingleLiveEvent<LoginResponse>> = _loginResponse

    var isValidInput = false
    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            val result = callService { authRepository.requestForLogin(loginRequest) }
            result?.data?.let {
                _loginResponse.value = com.mkrlabs.common.core.base.utils.SingleLiveEvent(it)
            }
        }
    }

    fun  isValid(email: EditText, password: EditText){
        if (email.text.isEmpty())email.error = "required"
        if (password.text.isEmpty())password.error = "required"
        isValidInput =  email.text.isNotEmpty() and  password.text.isNotEmpty()
    }
}