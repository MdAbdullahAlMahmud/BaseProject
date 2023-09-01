package com.mkrlabs.quiz.data.repositoryImpl

import android.util.Log
import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.quiz.data.repository.QuizRepository
import com.mkrlabs.quiz.data.services.QuizService
import retrofit2.Response
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizService: QuizService
): QuizRepository {

    override suspend fun requestQuizQuestionList(questionRequest: QuizQuestionRequest): Response<BaseResponse<List<QuizQuestionItem>>> {
        Log.v("Quiz",":::Service:::::::::::::::::::::::::::${questionRequest.quizId.toString()}:::::::::::::::::::::::::::::::::::")
        return quizService.getQuizQuestion(questionRequest.quizId.toString())
    }
}