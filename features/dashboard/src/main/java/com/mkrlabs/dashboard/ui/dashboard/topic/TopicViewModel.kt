package com.mkrlabs.dashboard.ui.dashboard.topic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.request.PDFItemRequest
import com.mkrlabs.dashboard.data.model.response.PDFItemResponse
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.data.repository.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val topicRepository: TopicRepository
): BaseViewModel(){

    private val _topicList = MutableLiveData<SingleLiveEvent<List<TopicItem>>>()
    val topicList : LiveData<SingleLiveEvent<List<TopicItem>>> = _topicList

    private val _subTopicList = MutableLiveData<SingleLiveEvent<List<SubTopicItem>>>()
    val subTopicList : LiveData<SingleLiveEvent<List<SubTopicItem>>> = _subTopicList

    private val _quizList = MutableLiveData<SingleLiveEvent<List<QuizResponseItem>>>()
    val quizList : LiveData<SingleLiveEvent<List<QuizResponseItem>>> = _quizList

    private val _pdfContent = MutableLiveData<SingleLiveEvent<PDFItemResponse>>()
    val pdfContent : LiveData<SingleLiveEvent<PDFItemResponse>> = _pdfContent



    fun getTopicList(topicId : String){
        viewModelScope.launch {
            val  result = callService { topicRepository.requestTopicList(topicId) }
            result?.data?.let {
                _topicList.value = SingleLiveEvent(it)
            }
        }
    }
    fun getPDFTopicList(topicId : String){
        viewModelScope.launch {
            val  result = callService { topicRepository.requestPDFTopicList(topicId) }
            result?.data?.let {
                _topicList.value = SingleLiveEvent(it)
            }
        }
    }


    fun getSubTopicList(subTopicRequest: SubTopicRequest){
        viewModelScope.launch {
            val  result = callService { topicRepository.requestSubTopicList(subTopicRequest) }
            result?.data?.let {
                _subTopicList.value = SingleLiveEvent(it)
            }
        }
    }

    fun getPDFSubTopicList(subTopicRequest: SubTopicRequest){
        viewModelScope.launch {
            val  result = callService { topicRepository.requestPDFSubTopicList(subTopicRequest) }
            result?.data?.let {
                _subTopicList.value = SingleLiveEvent(it)
            }
        }
    }



    fun getQuizList(quizRequestItem: QuizRequestItem){
        viewModelScope.launch {
            val  result = callService {  topicRepository.requestQuizList(quizRequestItem)}
            result?.data?.let {
                _quizList.value = SingleLiveEvent(it)
            }
        }
    }
    fun getPDFLessonList(quizRequestItem: QuizRequestItem){
        viewModelScope.launch {
            val  result = callService {  topicRepository.requestPDFLessonList(quizRequestItem)}
            result?.data?.let {
                _quizList.value = SingleLiveEvent(it)
            }
        }
    }
    fun getPdfContent(pdfItemRequest: PDFItemRequest){
        viewModelScope.launch {
            val  result = callService { topicRepository.requestPdfContent(pdfItemRequest)}
            result?.data?.let {
                _pdfContent.value = SingleLiveEvent(it)
            }
        }
    }









}