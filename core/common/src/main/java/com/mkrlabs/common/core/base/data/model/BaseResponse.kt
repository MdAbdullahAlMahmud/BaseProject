package com.mkrlabs.common.core.base.data.model

class BaseResponse<T> {
    var message: String? = ""
    var code: Int? = null
    var data: T? = null
}