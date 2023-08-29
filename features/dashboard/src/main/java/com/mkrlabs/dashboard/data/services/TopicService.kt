package com.mkrlabs.dashboard.data.services

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TopicService {

    @GET("/api.php?cat_list")
    suspend fun getTopicList() : Response<BaseResponse<List<TopicItem>>>

    @FormUrlEncoded
    @POST("/api.php?sub_cat_list")
    suspend fun getSubTopicList(
        @Field("cat_id") cat_id : String
    ) : Response<BaseResponse<List<SubTopicItem>>>
}