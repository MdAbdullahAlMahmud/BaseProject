package com.mkrlabs.dashboard

import android.text.BoringLayout
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.data.model.response.SubTopicItem
import com.mkrlabs.dashboard.data.model.response.TopicItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardHomeViewModel @Inject constructor():  BaseViewModel(){

    var featureItem : FeatureItem? = null
    var topicItem : TopicItem? = null
    var subTopicItem : SubTopicItem? = null
    var step : Int ? = null
    var topicId : String? = "0"
    var isPDF : Boolean? = false

}