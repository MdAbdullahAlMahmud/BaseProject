package com.mkrlabs.common.core.base.utils

fun String.includeDot():String{
    return "$this."
}

fun String?.quizTimeFormat() : String{
    if (this== null){
        return "0 : 00"
    }
    try {
        var seconds = this.toInt()
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60

        return String.format("%02d min : %02d s", minutes, remainingSeconds)
    }   catch (e : Exception){
        return "0 : 00"
    }
}