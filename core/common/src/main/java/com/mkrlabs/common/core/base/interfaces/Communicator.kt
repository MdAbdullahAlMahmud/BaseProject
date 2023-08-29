package com.mkrlabs.common.core.base.interfaces

interface Communicator {
    fun authenticationFailed(message: String? ="")

    fun gotoDashboard(navCode : Int = 0)
}