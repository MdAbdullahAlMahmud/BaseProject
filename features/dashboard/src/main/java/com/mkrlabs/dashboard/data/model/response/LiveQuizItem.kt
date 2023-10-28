package com.mkrlabs.dashboard.data.model.response

data class LiveQuizItem(
    val id : Int ? = null,
    val timestamp : String ?= null,
    val syllabus : List<String> ?= null,
    val title : String? = null,
    val questionSize : Int ?= null,
    val durationInMinute : Int ?= null,
    val isResultPublished : Boolean ?= null,
    val isLiveQuiz : Boolean ?= null,

)
