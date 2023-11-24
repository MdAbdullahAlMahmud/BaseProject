package com.mkrlabs.live_quiz.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.utils.SingleLiveEvent
import com.mkrlabs.live_quiz.data.model.QuizUserMetaData
import com.mkrlabs.live_quiz.data.model.request.QuizQuestionRequest
import com.mkrlabs.live_quiz.data.model.request.ScoreInsertRequest
import com.mkrlabs.live_quiz.data.model.response.QuizQuestionItem
import com.mkrlabs.live_quiz.data.repository.QuizRepository
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

    fun userQuizResult(timeTaken : Long , questionList : List<QuizQuestionItem>, userId : String , quizId : String, quizTime : String){

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
            metaData.timeTaken = formattedTakenTime(timeTaken)
            _quizUserMetaData.value = SingleLiveEvent(metaData)

            var score = ScoreInsertRequest(
                user_id =userId,
                qz_id = quizId,
                score  = metaData.correctAns,
                total_score = metaData.correctAns,
                total_time = metaData.timeTaken,
                quiz_time = quizTime,
                total_quiz_qs =questionList.size.toString(),
                un_answered =metaData.unAns,
                right_answered =metaData.correctAns,
                wrong_ans =metaData.wrongAns,

            )
            pushQuizResult(score)

        }

    }

    private fun pushQuizResult(score: ScoreInsertRequest){
        viewModelScope.launch {
            callService { quizRepository.insertQuizScore(score) }
        }
    }


    fun formattedTakenTime(millisUntilFinished : Long) : String{
        val minutes = millisUntilFinished / 60000
        val seconds = (millisUntilFinished % 60000) / 1000
        return (String.format("%02d:%02d", minutes, seconds))
    }




}