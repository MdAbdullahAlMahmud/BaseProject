package com.mkrlabs.dashboard.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.R
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.data.model.enums.Features
import com.mkrlabs.dashboard.data.model.enums.IndentityCode
import com.mkrlabs.dashboard.data.model.response.LeaderBoardItem
import com.mkrlabs.dashboard.data.model.response.LiveDashboardItem
import com.mkrlabs.dashboard.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  DashboardViewModel @Inject constructor(
    private val appRepository: AppRepository

):  BaseViewModel(){
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

    private val _liveQuizTopicList = MutableLiveData<SingleLiveEvent<List<LiveDashboardItem>>>()
    val liveQuizTopicList : LiveData<SingleLiveEvent<List<LiveDashboardItem>>> = _liveQuizTopicList








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
        list.add(FeatureItem(Features.MODEL_TEST.code,"মডেল টেস্ট","", R.drawable.model_test_icon))
        list.add(FeatureItem(Features.JOB_SOLUTION.code,"জব সল্যুশন","", R.drawable.job_solution_icon))

        _onusilonTopicList.value = SingleLiveEvent(list)
    }

     fun getVocabularyTopicList(){
        var list = arrayListOf<FeatureItem>()

        list.add(FeatureItem(Features.GRE_333.code,"GRE 333","", R.drawable.understanding1))
        list.add(FeatureItem(Features.GRE_1000.code,"GRE 1000","", R.drawable.english1))
        list.add(FeatureItem(Features.WORD_SMART_1.code,"Word Smart-1","", R.drawable.word1))
        list.add(FeatureItem(Features.WORD_SMART_2.code,"Word Smart-2","", R.drawable.word_smart_2_icon))
        list.add(FeatureItem(Features.MAGOOSH.code,"Magoosh","", R.drawable.magoosh_icon))
        list.add(FeatureItem(Features.MAHATTAN.code,"Manhattan","", R.drawable.manhattan_icon))
        list.add(FeatureItem(Features.PREVIOUS_VCAB.code,"Previous Vcab","", R.drawable.previous_vocanulary_icon))
        list.add(FeatureItem(Features.SPELLING.code,"Spelling","", R.drawable.spelling_icon))

         _vocabularyTopicList.value = SingleLiveEvent(list)
    }

    fun getLearnByHeartTopicList(){
        var list = arrayListOf<FeatureItem>()

        list.add(FeatureItem(Features.NEWSPAPER_EDITORIAL.code,"Newspaper Editorial","", R.drawable.newspaper1))
        list.add(FeatureItem(Features.IDOMS_AND_PHRASES.code,"Idiom & Phrase","", R.drawable.ok1))
        list.add(FeatureItem(Features.GROUP_VERB.code,"Group Verb","", R.drawable.puzzle1))
        list.add(FeatureItem(Features.APPROPRIATE_PREPOSITION.code,"Appropriate Preposition","", R.drawable.appropriate_preposition))
        list.add(FeatureItem(Features.ANALOGY.code,"Analogy","", R.drawable.analogy_icon))
        list.add(FeatureItem(Features.ONE_WORD_SUBJECT.code,"One Word Subject","", R.drawable.one_word_subject_icon))
        list.add(FeatureItem(Features.PROVERB.code,"Proverb","", R.drawable.proverb_icon))
        list.add(FeatureItem(Features.TRANSLATION.code,"Translation","", R.drawable.transaltion_icon))

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



    fun getLiveQuizTopicListTopicList(){

        viewModelScope.launch {
            val result = callService { appRepository.requestLiveQuizTopic() }
            result?.data?.let {
                _liveQuizTopicList.value = SingleLiveEvent(it)
            }
        }
    }













}