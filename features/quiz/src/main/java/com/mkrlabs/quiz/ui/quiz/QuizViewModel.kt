package com.mkrlabs.quiz.ui.quiz

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.quiz.data.model.QuizUserMetaData
import com.mkrlabs.quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.quiz.data.repository.QuizRepository
import com.mkrlabs.quiz.ui.QuizHomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : BaseViewModel() {

    private val _quizQuestionList = MutableLiveData<SingleLiveEvent<List<QuizQuestionItem>>>()
    val quizQuestionList: LiveData<SingleLiveEvent<List<QuizQuestionItem>>> = _quizQuestionList

    private val _quizUserMetaData = MutableLiveData<SingleLiveEvent<QuizUserMetaData>>()
    val quizUserMetaData: LiveData<SingleLiveEvent<QuizUserMetaData>> = _quizUserMetaData



    fun getQuizQuestionList(questionRequest: QuizQuestionRequest) {
        viewModelScope.launch {
            val result = callService { quizRepository.requestQuizQuestionList(questionRequest) }
            result?.data?.let {
                _quizQuestionList.value = SingleLiveEvent(it)
            }
        }

    }

    fun userQuizResult(questionList : List<QuizQuestionItem>){

        var correctAns = 0
        var wrongAns = 0
        var unAns = 0
        viewModelScope.launch {
            questionList.forEach {item->
                if(item.userChoice?.value.equals(item.answer)){
                    correctAns++

                }else if (item.userChoice == null){
                    unAns++
                }else{
                    wrongAns++
                }

            }

            var metaData = QuizUserMetaData()
            metaData.correctAns = correctAns.toString()
            metaData.wrongAns = wrongAns.toString()
            metaData.unAns = unAns.toString()
            metaData.totalQs = questionList.size.toString()
            metaData.topScoreQs = questionList.size.toString()
            metaData.timeTaken = "Empty"

            _quizUserMetaData.value = SingleLiveEvent(metaData)
        }

    }






}