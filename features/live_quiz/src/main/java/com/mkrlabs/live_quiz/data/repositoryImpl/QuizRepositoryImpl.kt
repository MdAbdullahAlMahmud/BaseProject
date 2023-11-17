package com.mkrlabs.live_quiz.data.repositoryImpl

import android.util.Log
import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.live_quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.live_quiz.data.model.request.ScoreInsertRequest
import com.mkrlabs.live_quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.live_quiz.data.model.response.ScoreInsertResponse
import com.mkrlabs.live_quiz.data.repository.QuizRepository
import com.mkrlabs.live_quiz.data.services.QuizService
import retrofit2.Response
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizService: QuizService
): QuizRepository {

    override suspend fun requestQuizQuestionList(questionRequest: QuizQuestionRequest): Response<BaseResponse<List<QuizQuestionItem>>> {
        Log.v("Quiz",":::Service:::::::::::::::::::::::::::${questionRequest.quizId.toString()}:::::::::::::::::::::::::::::::::::")
        return quizService.getLiveQuizQuestion(questionRequest.quizId.toString())
    }

    override suspend fun insertQuizScore(insertRequest: ScoreInsertRequest): Response<BaseResponse<ScoreInsertResponse>> {
        return quizService.insertScore(
            insertRequest.user_id.toString(),
            insertRequest.qz_id.toString(),
            insertRequest.score.toString(),
            insertRequest.total_score.toString(),
            insertRequest.total_time.toString(),
            insertRequest.quiz_time.toString()

        )
    }


}