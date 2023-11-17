package com.mkrlabs.dashboard.data.repository

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.common.core.base.data.model.response.LiveQuizResponseItem
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.request.PDFItemRequest
import com.mkrlabs.dashboard.data.model.response.LeaderBoardItem
import com.mkrlabs.dashboard.data.model.response.LiveDashboardItem
import com.mkrlabs.dashboard.data.model.response.PDFItemResponse
import com.mkrlabs.dashboard.data.model.response.SinglePdfCatItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AppRepository {
    suspend fun requestTopicList(topicId : String) : Response<BaseResponse<List<TopicItem>>>
    suspend fun requestPDFTopicList(topicId : String) : Response<BaseResponse<List<TopicItem>>>
    suspend fun requestSubTopicList(subTopicRequest: SubTopicRequest) : Response<BaseResponse<List<SubTopicItem>>>
    suspend fun requestPDFSubTopicList(subTopicRequest: SubTopicRequest) : Response<BaseResponse<List<SubTopicItem>>>
    suspend fun requestQuizList(quizRequestItem: QuizRequestItem) : Response<BaseResponse<List<QuizResponseItem>>>
    suspend fun requestLiveQuizList(liveQuizRequestItem: QuizRequestItem) : Response<BaseResponse<List<LiveQuizResponseItem>>>
    suspend fun requestPDFLessonList(quizRequestItem: QuizRequestItem) : Response<BaseResponse<List<QuizResponseItem>>>
    suspend fun requestPdfContent(pdfItemRequest: PDFItemRequest) : Response<BaseResponse<PDFItemResponse>>
    suspend fun requestSinglePdfCatList( id : String ) : Response<BaseResponse<List<SinglePdfCatItem>>>
    suspend fun requestSinglePdfId(cid : String, mid : String) : Response<BaseResponse<PDFItemResponse>>
    suspend fun requestForSinglePDFUrl(pid : String) : Response<BaseResponse<PDFItemResponse>>

    suspend fun requestLiveQuizTopic() : Response<BaseResponse<List<LiveDashboardItem>>>
    suspend fun requestLeaderBoard(qz_id : String) : Response<BaseResponse<List<LeaderBoardItem>>>

}