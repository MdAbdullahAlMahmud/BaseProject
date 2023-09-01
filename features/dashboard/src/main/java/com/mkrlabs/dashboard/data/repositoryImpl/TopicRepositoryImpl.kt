package com.mkrlabs.dashboard.data.repositoryImpl

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.data.repository.TopicRepository
import com.mkrlabs.dashboard.data.services.TopicService
import retrofit2.Response
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val topicService: TopicService
): TopicRepository {
    override suspend fun requestTopicList(): Response<BaseResponse<List<TopicItem>>> {
        return topicService.getTopicList()
    }

    override suspend fun requestSubTopicList(subTopicRequest: SubTopicRequest): Response<BaseResponse<List<SubTopicItem>>> {
        return  topicService.getSubTopicList(subTopicRequest.cat_id.toString())
    }

    override suspend fun requestQuizList(quizRequestItem: QuizRequestItem): Response<BaseResponse<List<QuizResponseItem>>> {
        return topicService.getQuizList(quizRequestItem.cat_id.toString(),quizRequestItem.sub_cat_id.toString())
    }
}