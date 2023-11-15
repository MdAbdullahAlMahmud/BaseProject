package com.mkrlabs.dashboard.data.model.enums

object IndentityCode {

    enum class QuizCategory(val code : String){
        TOPIC_VITTIK_ONUSILON("1"),
        TOPIC_VITTIK_PORIKKHA("2"),
        BISOY_VITTIK_PORIKKHA("3"),
        MODEL_TEST("4"),
        JOB_SOLUTION("5"),
    }

    enum class MiscellaneousCategory(val code : String){
        SAMPROTIK_THOTTHO("31"),
        TEXTBOOK("30"),
        SOMBADPOTRO("3"),
        BCS_SYLABUS_GUIDELINE("4"),
        BCS_BISLESON("5"),
        COURSE_ROUTINE("6"),
        NIYOG_BIGGOPPTI("7"),
        ANUBADOK("8"),
        ITIHASER_PATAI("32"),
        BANK_SYLABUS_GUIDELINE("10"),
    }


}