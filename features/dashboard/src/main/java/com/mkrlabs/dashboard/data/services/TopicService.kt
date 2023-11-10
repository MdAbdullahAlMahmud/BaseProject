package com.mkrlabs.dashboard.data.services

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.response.PDFItemResponse
import com.mkrlabs.dashboard.data.model.response.SinglePdfCatItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
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





}