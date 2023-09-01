package com.mkrlabs.quiz.data.model.response

data class QuizQuestionItem(
    val Choice_A: String ? = null,
    val Choice_B: String? = null,
    val Choice_C: String? = null,
    val Choice_D: String? = null,
    val Choice_E: String? = null,
    val answer: String? = null,
    val explanation: String? = null,
    val id: String? = null,
    val index_no: Int? = null,
    val no_of_choice: String? = null,
    val question: String? = null,
    val quiz_image: String? = null,
    val type: String? = null
)