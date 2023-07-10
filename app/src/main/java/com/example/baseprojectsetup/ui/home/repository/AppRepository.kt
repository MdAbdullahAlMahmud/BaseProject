package com.example.baseprojectsetup.ui.home.repository

import com.example.baseprojectsetup.core.base.data.model.BaseResponse
import com.example.baseprojectsetup.ui.home.model.request.RegisterDeviceRequest


/**
 * Created by Abdullah on 7/10/2023.
 */

interface AppRepository {
    suspend fun registerDevice(registerDeviceRequest : RegisterDeviceRequest)

}