package com.mkrlabs.quiz.ui

import com.mkrlabs.common.core.base.BaseViewModel
import com.mkrlabs.common.core.base.data.model.response.QuizResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizHomeViewModel  @Inject constructor():  BaseViewModel(){

    var quizItem  : QuizResponseItem ? = null

}