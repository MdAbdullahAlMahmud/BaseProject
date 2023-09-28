package com.example.baseprojectsetup.data.repository

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.example.baseprojectsetup.data.model.request.LoginRequest
import com.example.baseprojectsetup.data.model.request.RegistrationRequest
import com.example.baseprojectsetup.data.model.response.LoginResponse
import com.example.baseprojectsetup.data.model.response.RegistrationResponse
import retrofit2.Response

interface AuthRepository {

    suspend fun requestForLogin(loginRequest : LoginRequest) : Response<BaseResponse<LoginResponse>>
    suspend fun logOut() : Response<BaseResponse<Boolean>>
    suspend fun registration(registrationRequest: RegistrationRequest) : Response<BaseResponse<RegistrationResponse>>
}