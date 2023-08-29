package com.mkrlabs.dashboard.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkrlabs.common.R
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.data.model.enums.Features
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class  DashboardViewModel @Inject constructor():  BaseViewModel(){
    private val _qsBankTopicList = MutableLiveData<SingleLiveEvent<List<FeatureItem>>>()
    val qsBankTopicList : LiveData<SingleLiveEvent<List<FeatureItem>>> = _qsBankTopicList

    private val _onusilonTopicList = MutableLiveData<SingleLiveEvent<List<FeatureItem>>>()
    val onusilonTopicList : LiveData<SingleLiveEvent<List<FeatureItem>>> = _onusilonTopicList







    fun getQsBankTopicList(){
        var list = arrayListOf<FeatureItem>()

        list.add(FeatureItem(Features.TOPIC_VITTIK_JOB_SOLUTIONS.code,"টপিকভিত্তিক জব সল্যুশন","", R.drawable.topic1))
        list.add(FeatureItem(Features.BCS_PRELIMINARY.code,"BCS প্রিলিমিনারি","", R.drawable.businessman))
        list.add(FeatureItem(Features.BANK_NIYOG.code,"Bank নিয়োগ","", R.drawable.bank1))
        list.add(FeatureItem(Features.NINE_TEN_GRADE.code,"৯ম-১০ম গ্রেড","", R.drawable.employee))
        list.add(FeatureItem(Features.PRIMARY_AND_NTRCA.code,"প্রাইমারি & NTRCA","", R.drawable.textbook1))
        list.add(FeatureItem(Features.ELEVEN_TWENTY_GRADE.code,"১১ম- ২০ম গ্রেড","", R.drawable.podium1))
        _qsBankTopicList.value = SingleLiveEvent(list)
    }
    fun getOnusilonTopicList(){
        var list = arrayListOf<FeatureItem>()

        list.add(FeatureItem(Features.TOPIC_VITTIK_ONUSILON.code,"টপিকভিত্তিক অনুশীলন","", R.drawable.answer1))
        list.add(FeatureItem(Features.TOPIC_VITTIK_PORIKKHA.code,"টপিকভিত্তিক পরীক্ষা","", R.drawable.topic2))
        list.add(FeatureItem(Features.BISOY_VITTIK_PORIKKHA.code,"বিষয়ভিত্তিক পরীক্ষা","", R.drawable.book1))

        _onusilonTopicList.value = SingleLiveEvent(list)
    }



}