package com.mkrlabs.dashboard.data.repository

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.request.PDFItemRequest
import com.mkrlabs.dashboard.data.model.response.PDFItemResponse
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import retrofit2.Response

interface TopicRepository {
    suspend fun requestTopicList(topicId : String) : Response<BaseResponse<List<TopicItem>>>
    suspend fun requestPDFTopicList(topicId : String) : Response<BaseResponse<List<TopicItem>>>
    suspend fun requestSubTopicList(subTopicRequest: SubTopicRequest) : Response<BaseResponse<List<SubTopicItem>>>
    suspend fun requestPDFSubTopicList(subTopicRequest: SubTopicRequest) : Response<BaseResponse<List<SubTopicItem>>>
    suspend fun requestQuizList(quizRequestItem: QuizRequestItem) : Response<BaseResponse<List<QuizResponseItem>>>
    suspend fun requestPDFLessonList(quizRequestItem: QuizRequestItem) : Response<BaseResponse<List<QuizResponseItem>>>
    suspend fun requestPdfContent(pdfItemRequest: PDFItemRequest) : Response<BaseResponse<PDFItemResponse>>
}