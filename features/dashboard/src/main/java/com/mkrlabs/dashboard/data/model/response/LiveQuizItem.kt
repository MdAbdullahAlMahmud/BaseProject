package com.mkrlabs.dashboard.data.model.response

data class LiveQuizItem(
    val id : String ? = null,
    val timestamp : String ?= null,
    val syllabus : List<String> ?= null,
    val title : String? = null,
    val questionSize : String ?= null,
    val durationInMinute : String ?= null,
    val isResultPublished : Boolean ?= null,
    val isLiveQuiz : Boolean ?= null,

)
