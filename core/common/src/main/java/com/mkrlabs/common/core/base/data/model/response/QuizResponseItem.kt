package com.mkrlabs.common.core.base.data.model.response

import java.io.Serializable

class QuizResponseItem : Serializable{
    val cat_id: String ? = null
    val category_name: String? = null
    val no_of_question: String? = null
    val quiz_date: String? = null
    val quiz_image: String? = null
    val quiz_time: String? = null
    val quiz_title: String? = null
    val qz_id: String? = null
    val sub_cat_id: String? = null
    val pdf_url: String? = null
    override fun toString(): String {
        return "QuizResponseItem(cat_id=$cat_id, category_name=$category_name, no_of_question=$no_of_question, quiz_date=$quiz_date, quiz_image=$quiz_image, quiz_time=$quiz_time, quiz_title=$quiz_title, qz_id=$qz_id, sub_cat_id=$sub_cat_id)"
    }


}
