package com.mkrlabs.common.core.base.data.model.response

import java.io.Serializable

class LiveQuizResponseItem  (

    val title : String ?= null,
    val syllabus : String ?= null,
    val exam_date : String ?= null,
    val result_published_date : String ?= null,
    ) : QuizResponseItem()