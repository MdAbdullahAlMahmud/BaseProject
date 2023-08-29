package com.mkrlabs.dashboard.ui.dashboard.topic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.data.model.request.SubTopicRequest
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



    fun getTopicList(){
        viewModelScope.launch {
            val  result = callService { topicRepository.requestTopicList() }
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







}