package com.example.baseprojectsetup.core.base.data.model

class BaseResponse<T> {
    var responseMessage: String? = ""
    var responseCode: String? = ""
    var data: T? = null
}