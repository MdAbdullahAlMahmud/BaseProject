package com.mkrlabs.dashboard.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkrlabs.common.R
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.data.model.enums.Features
import com.mkrlabs.dashboard.data.model.enums.IndentityCode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class  DashboardViewModel @Inject constructor():  BaseViewModel(){
    private val _qsBankTopicList = MutableLiveData<SingleLiveEvent<List<FeatureItem>>>()
    val qsBankTopicList : LiveData<SingleLiveEvent<List<FeatureItem>>> = _qsBankTopicList

    private val _onusilonTopicList = MutableLiveData<SingleLiveEvent<List<FeatureItem>>>()
    val onusilonTopicList : LiveData<SingleLiveEvent<List<FeatureItem>>> = _onusilonTopicList

    private val _vocabularyTopicList = MutableLiveData<SingleLiveEvent<List<FeatureItem>>>()
    val vocabularyTopicList : LiveData<SingleLiveEvent<List<FeatureItem>>> = _vocabularyTopicList

    private val _learnByHeartTopicList = MutableLiveData<SingleLiveEvent<List<FeatureItem>>>()
    val learnByHeartTopicList : LiveData<SingleLiveEvent<List<FeatureItem>>> = _learnByHeartTopicList

    private val _miscellaneousTopicList = MutableLiveData<SingleLiveEvent<List<FeatureItem>>>()
    val miscellaneousTopicList : LiveData<SingleLiveEvent<List<FeatureItem>>> = _miscellaneousTopicList








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

     fun getVocabularyTopicList(){
        var list = arrayListOf<FeatureItem>()

        list.add(FeatureItem(Features.GRE_333.code,"GRE 333","", R.drawable.understanding1))
        list.add(FeatureItem(Features.GRE_1000.code,"GRE 1000","", R.drawable.english1))
        list.add(FeatureItem(Features.WORD_SMART_1.code,"Word Smart-1","", R.drawable.word1))

         _vocabularyTopicList.value = SingleLiveEvent(list)
    }

    fun getLearnByHeartTopicList(){
        var list = arrayListOf<FeatureItem>()

        list.add(FeatureItem(Features.NEWSPAPER_EDITORIAL.code,"Newspaper Editorial","", R.drawable.newspaper1))
        list.add(FeatureItem(Features.IDOMS_AND_PHRASES.code,"Idiom & Phrase","", R.drawable.ok1))
        list.add(FeatureItem(Features.GROUP_VERB.code,"Group Verb","", R.drawable.puzzle1))

        _learnByHeartTopicList.value = SingleLiveEvent(list)
    }
    fun getMiscellaneousTopicListTopicList(){
        var list = arrayListOf<FeatureItem>()

        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.SAMPROTIK_THOTTHO.code,"সাম্প্রতিক তথ্য","", R.drawable.samprotik_tottho_icon))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.TEXTBOOK.code,"টেক্সটবুক","", R.drawable.textbook_icon_misc))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.SOMBADPOTRO.code,"সংবাদপত্র","", R.drawable.sombadpotro_icon))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.BCS_SYLABUS_GUIDELINE.code,"BCS সিলেবাস \n&গাইডলাইন","", R.drawable.bcs_sylabus_guideline))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.BCS_BISLESON.code,"BCS প্রশ্ন \nবিশ্লেষণ","", R.drawable.bcs_prosno_bisleson))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.COURSE_ROUTINE.code,"কোর্স রুটিন","", R.drawable.course_routine))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.NIYOG_BIGGOPPTI.code,"নিয়োগ বিজ্ঞপ্তি","", R.drawable.niyog_bigopti_icon))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.ANUBADOK.code,"অনুবাদক","", R.drawable.onubadok_icon))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.ITIHASER_PATAI.code,"ইতিহাসের \n পাতায়","", R.drawable.itihaser_patai_icon))
        list.add(FeatureItem(IndentityCode.MiscellaneousCategory.BANK_SYLABUS_GUIDELINE.code,"Bank সিলেবাস \n&গাইডলাইন","", R.drawable.bank_sylabus_guideline))
        _miscellaneousTopicList.value = SingleLiveEvent(list)
    }










}