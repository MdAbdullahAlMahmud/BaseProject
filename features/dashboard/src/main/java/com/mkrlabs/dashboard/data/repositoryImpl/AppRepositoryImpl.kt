package com.mkrlabs.dashboard.data.repositoryImpl

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.request.PDFItemRequest
import com.mkrlabs.dashboard.data.model.response.PDFItemResponse
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.data.repository.AppRepository
import com.mkrlabs.dashboard.data.services.TopicService
import retrofit2.Response
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val topicService: TopicService
): AppRepository {
    override suspend fun requestTopicList(topicId : String): Response<BaseResponse<List<TopicItem>>> {
        return topicService.getTopicList(topicId)
    }
    override suspend fun requestPDFTopicList(topicId : String): Response<BaseResponse<List<TopicItem>>> {
        return topicService.getPDFTopicList(topicId)
    }

    override suspend fun requestSubTopicList(subTopicRequest: SubTopicRequest): Response<BaseResponse<List<SubTopicItem>>> {
        return  topicService.getSubTopicList(subTopicRequest.cat_id.toString())
    }
    override suspend fun requestPDFSubTopicList(subTopicRequest: SubTopicRequest): Response<BaseResponse<List<SubTopicItem>>> {
        return  topicService.getPDFSubTopicList(subTopicRequest.cat_id.toString())
    }

    override suspend fun requestQuizList(quizRequestItem: QuizRequestItem): Response<BaseResponse<List<QuizResponseItem>>> {
        return topicService.getQuizList(quizRequestItem.cat_id.toString(),quizRequestItem.sub_cat_id.toString())
    }
    override suspend fun requestPDFLessonList(quizRequestItem: QuizRequestItem): Response<BaseResponse<List<QuizResponseItem>>> {
        return topicService.getPDFLessonList(quizRequestItem.cat_id.toString(),quizRequestItem.sub_cat_id.toString())
    }

    override suspend fun requestPdfContent(pdfItemRequest: PDFItemRequest): Response<BaseResponse<PDFItemResponse>> {
        return topicService.getPDFContent(pdfItemRequest.pdf_id.toString())
    }



}