package com.example.baseprojectsetup.ui.home.model.request

data class RegisterDeviceRequest(
    var appLanguage: String = "",
    var appVersion: String = "",
    var deviceIp: String = "",
    var deviceModel: String = "",
    var deviceName: String = "",
    var deviceOS: String = "",
    var deviceOsVersion: String = "",
    var deviceType: Int = 1,
    var deviceUniqueId: String = "",
    var pushToken: String = "",
)
