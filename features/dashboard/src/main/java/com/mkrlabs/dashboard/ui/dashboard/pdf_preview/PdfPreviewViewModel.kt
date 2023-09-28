package com.mkrlabs.dashboard.ui.dashboard.pdf_preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.dashboard.data.model.request.PDFItemRequest
import com.mkrlabs.dashboard.data.model.response.PDFItemResponse
import com.mkrlabs.dashboard.data.model.response.TopicItem
import com.mkrlabs.dashboard.data.repository.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PdfPreviewViewModel @Inject constructor(
    private val topicRepository: TopicRepository

): BaseViewModel(){

    private val _pdfContent = MutableLiveData<SingleLiveEvent<PDFItemResponse>>()
    val pdfContent : LiveData<SingleLiveEvent<PDFItemResponse>> = _pdfContent
    fun requestForPdf(pdfRequest : PDFItemRequest){
        viewModelScope.launch {
        val result = callService { topicRepository.requestPdfContent(pdfRequest) }
            result?.data?.let {
                _pdfContent.value = SingleLiveEvent(it)
            }
        }

    }



}