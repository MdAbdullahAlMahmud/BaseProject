package com.mkrlabs.dashboard.data.repository

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import retrofit2.Response

interface TopicRepository {
    suspend fun requestTopicList() : Response<BaseResponse<List<TopicItem>>>
    suspend fun requestSubTopicList(subTopicRequest: SubTopicRequest) : Response<BaseResponse<List<SubTopicItem>>>
    suspend fun requestQuizList(quizRequestItem: QuizRequestItem) : Response<BaseResponse<List<QuizResponseItem>>>
}