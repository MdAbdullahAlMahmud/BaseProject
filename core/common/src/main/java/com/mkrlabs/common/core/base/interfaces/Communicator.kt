package com.mkrlabs.common.core.base.interfaces

import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem


interface Communicator {
    fun authenticationFailed(message: String? ="")

    fun gotoDashboard(navCode : Int = 0)
    fun gotoQuizActivity(quizItem : QuizResponseItem? = null)
    fun gotoLiveQuizActivity(quizItem : QuizResponseItem? = null)
}