package com.mkrlabs.dashboard.data.model.enums

object IndentityCode {

    enum class QuizCategory(val code : String){
        TOPIC_VITTIK_ONUSILON("1"),
        TOPIC_VITTIK_PORIKKHA("2"),
        BISOY_VITTIK_PORIKKHA("3"),
    }

    enum class MiscellaneousCategory(val code : String){
        SAMPROTIK_THOTTHO("901"),
        TEXTBOOK("902"),
        SOMBADPOTRO("903"),
        BCS_SYLABUS_GUIDELINE("904"),
        BCS_BISLESON("905"),
        COURSE_ROUTINE("906"),
        NIYOG_BIGGOPPTI("907"),
        ANUBADOK("908"),
        ITIHASER_PATAI("909"),
        BANK_SYLABUS_GUIDELINE("910"),
    }


}