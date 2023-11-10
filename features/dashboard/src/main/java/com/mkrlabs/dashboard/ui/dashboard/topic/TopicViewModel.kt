package com.mkrlabs.dashboard.ui.dashboard.topic

import android.util.Log
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
import com.mkrlabs.dashboard.data.model.response.SinglePdfCatItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val appRepository: AppRepository
): BaseViewModel(){

    private val _topicList = MutableLiveData<SingleLiveEvent<List<TopicItem>>>()
    val topicList : LiveData<SingleLiveEvent<List<TopicItem>>> = _topicList

    private val _subTopicList = MutableLiveData<SingleLiveEvent<List<SubTopicItem>>>()
    val subTopicList : LiveData<SingleLiveEvent<List<SubTopicItem>>> = _subTopicList

    private val _quizList = MutableLiveData<SingleLiveEvent<List<QuizResponseItem>>>()
    val quizList : LiveData<SingleLiveEvent<List<QuizResponseItem>>> = _quizList

    private val _pdfContent = MutableLiveData<SingleLiveEvent<PDFItemResponse>>()
    val pdfContent : LiveData<SingleLiveEvent<PDFItemResponse>> = _pdfContent

    private val _pdfUrlResponse = MutableLiveData<SingleLiveEvent<PDFItemResponse>>()
    val pdfUrlResponse : LiveData<SingleLiveEvent<PDFItemResponse>> = _pdfUrlResponse



    private val _singleTopicList = MutableLiveData<SingleLiveEvent<List<SinglePdfCatItem>>>()
    val singleTopicList : LiveData<SingleLiveEvent<List<SinglePdfCatItem>>> = _singleTopicList


    fun getTopicList(topicId : String){
        viewModelScope.launch {
            val  result = callService { appRepository.requestTopicList(topicId) }
            result?.data?.let {
                _topicList.value = SingleLiveEvent(it)
            }
        }
    }
    fun getPDFTopicList(topicId : String){
        viewModelScope.launch {
            val  result = callService { appRepository.requestPDFTopicList(topicId) }
            result?.data?.let {
                _topicList.value = SingleLiveEvent(it)
            }
        }
    }
    fun getSinglePDFTopicList(topicId: String) {
        viewModelScope.launch {
            val result = callService { appRepository.requestSinglePdfCatList(topicId) }
            result?.data?.let {
                _singleTopicList.value = SingleLiveEvent(it)
            }
        }
    }


    fun getSubTopicList(subTopicRequest: SubTopicRequest){
        viewModelScope.launch {
            val  result = callService { appRepository.requestSubTopicList(subTopicRequest) }
            result?.data?.let {
                _subTopicList.value = SingleLiveEvent(it)
            }
        }
    }

    fun getPDFSubTopicList(subTopicRequest: SubTopicRequest){
        viewModelScope.launch {
            val  result = callService { appRepository.requestPDFSubTopicList(subTopicRequest) }
            result?.data?.let {
                _subTopicList.value = SingleLiveEvent(it)
            }
        }
    }



    fun getQuizList(quizRequestItem: QuizRequestItem){
        viewModelScope.launch {
            val  result = callService {  appRepository.requestQuizList(quizRequestItem)}
            result?.data?.let {
                _quizList.value = SingleLiveEvent(it)
            }
        }
    }
    fun getPDFLessonList(quizRequestItem: QuizRequestItem){
        viewModelScope.launch {
            val  result = callService {  appRepository.requestPDFLessonList(quizRequestItem)}
            result?.data?.let {
                _quizList.value = SingleLiveEvent(it)
            }
        }
    }
    fun getPdfContent(pdfItemRequest: PDFItemRequest){
        viewModelScope.launch {
            val  result = callService { appRepository.requestPdfContent(pdfItemRequest)}
            result?.data?.let {
                _pdfContent.value = SingleLiveEvent(it)
            }
        }
    }


    fun requestForPdfId(cid : String , mid : String){
        viewModelScope.launch {
            val  result = callService { appRepository.requestSinglePdfId(cid,mid)}
            result?.data?.let {
                _pdfContent.value = SingleLiveEvent(it)
            }
        }
    }

    fun requestForSinglePDFUrl(pdfId: String){
        viewModelScope.launch {
            val  result = callService { appRepository.requestForSinglePDFUrl(pdfId)}
            result?.data?.let {
                _pdfUrlResponse.value = SingleLiveEvent(it)
            }
        }
    }










}