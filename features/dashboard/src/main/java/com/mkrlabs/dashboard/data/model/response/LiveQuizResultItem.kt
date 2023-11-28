package com.mkrlabs.dashboard.data.model.response

data class LiveQuizResultItem(
    val count: Int ? =null,
    val quiz_time: String? =null,
    val qz_id: String? =null,
    val rank: String? =null,
    val right_answered: String? =null,
    val score: String? =null,
    val total_quiz_qs: String? =null,
    val total_score: String? =null,
    val total_time: String? =null,
    val un_answered: String? =null,
    val user_id: String? =null,
    val wrong_ans: String? =null,
    val total_pass_users: String? =null,
    val total_fail_users: String? =null
)