package com.example.baseprojectsetup.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseprojectsetup.data.model.request.RegistrationRequest
import com.example.baseprojectsetup.data.model.response.RegistrationResponse
import com.mkrlabs.common.core.base.BaseViewModel
import com.example.baseprojectsetup.data.repository.AuthRepository
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel  @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _registrationResponse = MutableLiveData<SingleLiveEvent<RegistrationResponse>>()
    val registrationResponse : LiveData<SingleLiveEvent<RegistrationResponse>> = _registrationResponse


    fun requestRegistration(registrationRequest: RegistrationRequest){

        viewModelScope.launch {
            val  result = callService { authRepository.registration(registrationRequest) }
            result?.data?.let {
             _registrationResponse.value = SingleLiveEvent(it)
            }

        }
    }


}