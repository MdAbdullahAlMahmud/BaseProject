package com.example.baseprojectsetup.data.model.request

data class RegistrationRequest (
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var deviceId: String? = null,
    var phone: String? = null,
)