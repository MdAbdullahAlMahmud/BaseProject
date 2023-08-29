package com.example.baseprojectsetup.data.services

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.example.baseprojectsetup.data.model.request.LoginRequest
import com.example.baseprojectsetup.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("/api.php?login")
    suspend fun requestLogin(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("deviceId") deviceId : String,
    )
    : Response<BaseResponse<LoginResponse>>

    @GET("/api/v1/logout")
    suspend fun logOut() : Response<BaseResponse<Boolean>>
}