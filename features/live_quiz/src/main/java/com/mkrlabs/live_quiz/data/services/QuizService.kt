package com.mkrlabs.live_quiz.data.services

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.live_quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.live_quiz.data.model.response.ScoreInsertResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface QuizService {

    @FormUrlEncoded
    @POST("api.php?getLivetQuestion")
    suspend fun getLiveQuizQuestion(
        @Field("qid") qid : String
    ) : Response<BaseResponse<List<QuizQuestionItem>>>


    @FormUrlEncoded
    @POST("api.php?live_quiz_score_store")
    suspend fun insertScore(
        @Field("user_id") qid : String,
        @Field("qz_id") qz_id : String,
        @Field("score") score : String,
        @Field("total_score") total_score : String,
        @Field("total_time") total_time : String,
        @Field("quiz_time") quiz_time : String,
        @Field("total_quiz_qs") total_quiz_qs : String,
        @Field("wrong_ans") wrong_ans : String,
        @Field("un_answered") un_answered : String,
        @Field("right_answered") right_answered : String,
    ) : Response<BaseResponse<ScoreInsertResponse>>





}