package com.mkrlabs.common.core.base.interfaces

object CommunicatorImpl {
    var callback: Communicator? = null
    fun setCommunicator(communicator: Communicator) {
        callback = communicator
    }
}