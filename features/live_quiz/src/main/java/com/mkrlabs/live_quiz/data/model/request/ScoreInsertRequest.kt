package com.mkrlabs.live_quiz.data.model.request

data class ScoreInsertRequest (
    val user_id : String ? = null,
    val qz_id : String ? = null,
    val score : String ? = null,
    val total_score : String ? = null,
    val total_time : String ? = null,
    val quiz_time : String ? = null,
)
