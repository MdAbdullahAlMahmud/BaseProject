package com.mkrlabs.live_quiz.data.repository

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.live_quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.live_quiz.data.model.request.ScoreInsertRequest
import com.mkrlabs.live_quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.live_quiz.data.model.response.ScoreInsertResponse
import retrofit2.Response

interface QuizRepository  {
    suspend fun requestQuizQuestionList(questionRequest: QuizQuestionRequest) : Response<BaseResponse<List<QuizQuestionItem>>>
    suspend fun insertQuizScore(insertRequest: ScoreInsertRequest) : Response<BaseResponse<ScoreInsertResponse>>

}