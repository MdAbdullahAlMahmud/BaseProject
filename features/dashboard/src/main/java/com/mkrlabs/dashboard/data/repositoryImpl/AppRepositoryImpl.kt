package com.mkrlabs.dashboard.data.repositoryImpl

import com.mkrlabs.common.core.base.data.model.BaseResponse
import com.mkrlabs.common.core.base.data.model.response.LiveQuizResponseItem
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.request.PDFItemRequest
import com.mkrlabs.dashboard.data.model.request.UserRequest
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

    override suspend fun requestSinglePdfCatList(id: String): Response<BaseResponse<List<SinglePdfCatItem>>> {
        return topicService.getSinglePDFCatList(id)
    }

    override suspend fun requestSinglePdfId(
        cid: String,
        mid: String
    ): Response<BaseResponse<PDFItemResponse>> {
        return topicService.getSinglePdfId(cid,mid)
    }

    override suspend fun requestForSinglePDFUrl(pid: String): Response<BaseResponse<PDFItemResponse>> {
        return topicService.getPDFContent(pid)
    }

    override suspend fun requestLiveQuizTopic(): Response<BaseResponse<List<LiveDashboardItem>>> {
        return topicService.requestLiveTopicList()
    }


    override suspend fun requestLiveQuizList(liveQuizRequestItem: QuizRequestItem): Response<BaseResponse<List<LiveQuizResponseItem>>> {
        return topicService.getLiveQuizList(liveQuizRequestItem.cat_id.toString(),liveQuizRequestItem.sub_cat_id.toString())
    }
    override suspend fun requestLeaderBoard(qz_id: String): Response<BaseResponse<List<LeaderBoardItem>>> {
        return topicService.requestLeaderBoardList(qz_id)
    }

     override suspend fun requestUserResult(user_id : String, qz_id: String): Response<BaseResponse<LiveQuizResultItem>> {
        return topicService.requestUserResult(user_id,qz_id)
    }

    override suspend fun requestUserInfo(user_id: String): Response<BaseResponse<User>> {
        return  topicService.requestUserInfo(user_id)
    }

    override suspend fun requestUserUpdate(request: UserRequest): Response<BaseResponse<UserUpdateResponse>> {
       return  topicService.updateUser(request.id.toString(),request.name.toString(),request.phone.toString() )
    }

    override suspend fun requestNotificationList(): Response<BaseResponse<List<NotificationItem>>> {
        return  topicService.notificationList()
    }



}