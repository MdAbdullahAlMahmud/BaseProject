package com.example.baseprojectsetup.data.repositorylmpl

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.example.baseprojectsetup.data.repository.AuthRepository
import com.example.baseprojectsetup.data.model.request.LoginRequest
import com.example.baseprojectsetup.data.model.request.RegistrationRequest
import com.example.baseprojectsetup.data.model.response.LoginResponse
import com.example.baseprojectsetup.data.model.response.RegistrationResponse
import com.example.baseprojectsetup.data.services.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
): AuthRepository  {
    override suspend fun requestForLogin(loginRequest: LoginRequest): Response<com.mkrlabs.common.core.base.data.model.BaseResponse<LoginResponse>> {
        return authService.requestLogin(loginRequest.email!!,loginRequest.password!!,loginRequest.deviceId!!)
    }

    override suspend fun logOut(): Response<com.mkrlabs.common.core.base.data.model.BaseResponse<Boolean>> {
        return authService.logOut()
    }

    override suspend fun registration(registrationRequest: RegistrationRequest): Response<BaseResponse<RegistrationResponse>> {
        return authService.requestRegistration(
             name = registrationRequest.name.toString(),
             email = registrationRequest.email.toString(),
             phone = registrationRequest.phone.toString(),
            password = registrationRequest.password.toString(),
            deviceId = registrationRequest.deviceId.toString()
        )
    }

}