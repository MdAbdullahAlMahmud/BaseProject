package com.mkrlabs.dashboard.ui.dashboard.live_exam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.data.model.response.LiveQuizResponseItem
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.dashboard.data.model.FeatureItem
import com.mkrlabs.dashboard.data.model.request.QuizRequestItem
import com.mkrlabs.dashboard.data.model.response.LiveQuizItem
import com.mkrlabs.dashboard.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveQuizViewModel @Inject constructor(
    private val appRepository: AppRepository

): BaseViewModel(){

    private val _liveQuizList = MutableLiveData<SingleLiveEvent<List<LiveQuizResponseItem>>>()
    val liveQuizList : LiveData<SingleLiveEvent<List<LiveQuizResponseItem>>> = _liveQuizList


    private val _liveQuizAllList = MutableLiveData<SingleLiveEvent<List<QuizResponseItem>>>()
    val liveAllQuizList : LiveData<SingleLiveEvent<List<QuizResponseItem>>> = _liveQuizAllList
    fun  requestLiveQuizList(){

        viewModelScope.launch {

            var list = arrayListOf<LiveQuizItem>()

            /*list.add(LiveQuizItem(id = 1, title = "Exam One 1 " , isResultPublished = true))
            list.add(LiveQuizItem(id = 2, title = "Exam One 2 ", isResultPublished = false))
            list.add(LiveQuizItem(id = 3, title = "Exam One 3 ", isResultPublished = true ))
            list.add(LiveQuizItem(id = 4, title = "Exam One 4 ", isResultPublished = false))
            list.add(LiveQuizItem(id = 5, title = "Exam One 5 ", isResultPublished = false))*/

            //_liveQuizList.value = SingleLiveEvent(list)

        }

    }
    fun getLiveQuizList(quizRequestItem: QuizRequestItem){
        viewModelScope.launch {
            val  result = callService {  appRepository.requestLiveQuizList(quizRequestItem)}
            result?.data?.let {
                print("------------------${it.toString()}-----------------------")
                /*var list = arrayListOf<LiveQuizItem>()

                //_liveQuizList.value = SingleLiveEvent(list)
                it.forEach {quizItem ->
                    list.add(LiveQuizItem(
                        id = quizItem.qz_id,
                        title = quizItem.quiz_title ,
                        isResultPublished = true ,
                        questionSize = quizItem.no_of_question,
                        timestamp = quizItem.quiz_date,
                        durationInMinute = quizItem.quiz_time

                        ))


                }*/
                _liveQuizList.value = SingleLiveEvent(it)
            }
        }
    }
}