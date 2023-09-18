package com.mkrlabs.quiz.data.services

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface QuizService {

    @FormUrlEncoded
    @POST("/edubee/api.php?getQuestion")
    suspend fun getQuizQuestion(
        @Field("sub_quiz_list") sub_quiz_list : String
    ) : Response<BaseResponse<List<QuizQuestionItem>>>
}