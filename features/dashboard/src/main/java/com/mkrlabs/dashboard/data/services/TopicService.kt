package com.mkrlabs.dashboard.data.services

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TopicService {

    @FormUrlEncoded
    @POST("/edubee/api.php?cat_list")
    suspend fun getTopicList(
        @Field("topic_id") topic_id : String
    ) : Response<BaseResponse<List<TopicItem>>>

    @FormUrlEncoded
    @POST("/edubee/api.php?sub_cat_list")
    suspend fun getSubTopicList(
        @Field("cat_id") cat_id : String
    ) : Response<BaseResponse<List<SubTopicItem>>>

    @FormUrlEncoded
    @POST("/edubee/api.php?quiz_list")
    suspend fun getQuizList(
        @Field("cat_id") cat_id : String,
        @Field("sub_cat_id") sub_cat_id : String
    ) : Response<BaseResponse<List<QuizResponseItem>>>


}