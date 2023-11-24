package com.mkrlabs.common.core.base.data.model.response

import java.io.Serializable

class LiveQuizResponseItem  (

    val title : String ?= null,
    val syllabus : String ?= null,
    val exam_date : String ?= null,
    val exam_time : String ?= null,
    val result_published_date : String ?= null,
    val result_published_time : String ?= null,
    val cut_marks : String ?= null,
    val pass_marks : String ?= null,
    ) : QuizResponseItem()