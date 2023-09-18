package com.mkrlabs.quiz.data.model

data class QuizUserMetaData (
    var timeTaken : String ? = null,
    var correctAns : String ? = null,
    var totalQs : String ? = null,
    var topScoreQs : String ? = null,
    var wrongAns : String ? = null,
    var unAns : String ? = null

)