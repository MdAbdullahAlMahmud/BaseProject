package com.mkrlabs.common.core.base.utils

enum class ResponseCode(val value : Int) {
    SUCCESSFULL(200),
    RECORD_NOT_FOUND(404),
    BAD_REQUEST(409),
}