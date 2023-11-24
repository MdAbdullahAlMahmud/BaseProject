package com.mkrlabs.dashboard.data.services

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.common.core.base.data.model.response.LiveQuizResponseItem
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.response.LeaderBoardItem
import com.mkrlabs.dashboard.data.model.response.LiveDashboardItem
import com.mkrlabs.dashboard.data.model.response.LiveQuizResultItem
import com.mkrlabs.dashboard.data.model.response.NotificationItem
import com.mkrlabs.dashboard.data.model.response.PDFItemResponse
import com.mkrlabs.dashboard.data.model.response.SinglePdfCatItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.data.model.response.User
import com.mkrlabs.dashboard.data.model.response.UserUpdateResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TopicService {

    @FormUrlEncoded
    @POST("api.php?cat_list")
    suspend fun getTopicList(
        @Field("topic_id") topic_id : String
    ) : Response<BaseResponse<List<TopicItem>>>

    @FormUrlEncoded
    @POST("api.php?sub_cat_list")
    suspend fun getSubTopicList(
        @Field("cat_id") cat_id : String
    ) : Response<BaseResponse<List<SubTopicItem>>>

    @FormUrlEncoded
    @POST("api.php?quiz_list")
    suspend fun getQuizList(
        @Field("cat_id") cat_id : String,
        @Field("sub_cat_id") sub_cat_id : String
    ) : Response<BaseResponse<List<QuizResponseItem>>>


    @FormUrlEncoded
    @POST("api.php?live_quiz_list")
    suspend fun getLiveQuizList(
        @Field("cat_id") cat_id : String,
        @Field("sub_cat_id") sub_cat_id : String
    ) : Response<BaseResponse<List<LiveQuizResponseItem>>>







    //PDF Content
    @FormUrlEncoded
    @POST("api.php?pdf_cat_list")
    suspend fun getPDFTopicList(
        @Field("topic_id") topic_id : String
    ) : Response<BaseResponse<List<TopicItem>>>

    @FormUrlEncoded
    @POST("api.php?pdf_sub_cat_list")
    suspend fun getPDFSubTopicList(
        @Field("cat_id") cat_id : String
    ) : Response<BaseResponse<List<SubTopicItem>>>


    @FormUrlEncoded
    @POST("api.php?pdf_lesson_list")
    suspend fun getPDFLessonList(
        @Field("cat_id") cat_id : String,
        @Field("sub_cat_id") sub_cat_id : String
    ) : Response<BaseResponse<List<QuizResponseItem>>>

    @FormUrlEncoded
    @POST("api.php?getPdfContent")
    suspend fun getPDFContent(
        @Field("pdf_id") pdf_id : String,
    ) : Response<BaseResponse<PDFItemResponse>>
    @FormUrlEncoded
    @POST("api.php?single_pdf_cat_list")
    suspend fun getSinglePDFCatList(
        @Field("topic_id") id : String,
    ) : Response<BaseResponse<List<SinglePdfCatItem>>>

    @FormUrlEncoded
    @POST("api.php?single_pdf_id")
    suspend fun getSinglePdfId(
        @Field("cat_id") mid : String,
        @Field("sub_cat_id") cid : String,
    ) : Response<BaseResponse<PDFItemResponse>>

    @GET("api.php?liveQuizTopic")
    suspend fun requestLiveTopicList() : Response<BaseResponse<List<LiveDashboardItem>>>

    @FormUrlEncoded
    @POST("api.php?leaderboard")
    suspend fun requestLeaderBoardList(
        @Field("qz_id") qid : String
    ) : Response<BaseResponse<List<LeaderBoardItem>>>

    @FormUrlEncoded
    @POST("api.php?get_score_detail")
    suspend fun requestUserResult(
        @Field("user_id") user_id : String,
        @Field("qz_id") qid : String
    ) : Response<BaseResponse<LiveQuizResultItem>>

    @FormUrlEncoded
    @POST("api.php?profile")
    suspend fun requestUserInfo(
        @Field("id") user_id : String,
    ) : Response<BaseResponse<User>>

    @FormUrlEncoded
    @POST("api.php?update_profile")
    suspend fun updateUser(
        @Field("user_id") user_id : String,
        @Field("name") name : String,
        @Field("phone") phone : String,
    ) : Response<BaseResponse<UserUpdateResponse>>


    @GET("api.php?notification_list")
    suspend fun notificationList() : Response<BaseResponse<List<NotificationItem>>>













}