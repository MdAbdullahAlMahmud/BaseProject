package com.mkrlabs.quiz.data.repository

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import retrofit2.Response

interface QuizRepository  {
    suspend fun requestQuizQuestionList(questionRequest: QuizQuestionRequest) : Response<BaseResponse<List<QuizQuestionItem>>>
}