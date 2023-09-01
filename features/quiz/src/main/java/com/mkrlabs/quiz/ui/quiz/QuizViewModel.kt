package com.mkrlabs.quiz.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.quiz.data.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : BaseViewModel() {

    private val _quizQuestionList = MutableLiveData<SingleLiveEvent<List<QuizQuestionItem>>>()
    val quizQuestionList: LiveData<SingleLiveEvent<List<QuizQuestionItem>>> = _quizQuestionList


    fun getQuizQuestionList(questionRequest: QuizQuestionRequest) {
        viewModelScope.launch {
            val result = callService { quizRepository.requestQuizQuestionList(questionRequest) }
            result?.data?.let {
                _quizQuestionList.value = SingleLiveEvent(it)
            }
        }

    }


}