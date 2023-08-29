package com.example.baseprojectsetup.data.repository

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.example.baseprojectsetup.data.model.request.LoginRequest
import com.example.baseprojectsetup.data.model.response.LoginResponse
import retrofit2.Response

interface AuthRepository {

    suspend fun requestForLogin(loginRequest : LoginRequest) : Response<com.mkrlabs.common.core.base.data.model.BaseResponse<LoginResponse>>
    suspend fun logOut() : Response<com.mkrlabs.common.core.base.data.model.BaseResponse<Boolean>>
}