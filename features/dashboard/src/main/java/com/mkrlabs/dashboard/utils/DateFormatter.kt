package com.mkrlabs.dashboard.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateFormatter {

    fun getDayNameFromDate(dateString: String): String {
        //val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val date = formatter.parse(dateString) ?: Date()

        val calendar = Calendar.getInstance()
        calendar.time = date

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val dayName = SimpleDateFormat("EEEE", Locale.getDefault()).format(date)

        return dayNameInBangla(dayOfWeek)
    }


    fun examDateAndTodaysDateIsSame(examDate : String) : Boolean{
        val currentDate = Date()
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val today =  formatter.format(currentDate)
        return today.equals(examDate)
    }

    fun resultPublishedDateAndTodaysDateIsSame(published : String) : Boolean{
        try {
            val currentDate = Date()
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val today =  formatter.format(currentDate)
            return today.equals(published) || isDateGreaterThanToday(published)
        }catch (e : Exception){
            e.printStackTrace()
            return false
        }

    }
    fun isDateGreaterThanToday(dateString: String): Boolean {
        try {
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            // Parse the given date

            val givenDate = dateFormat.parse(dateString)

            // Get today's date
            val todayDate = Date()

            // Compare the dates
            if (givenDate != null) {
                return givenDate.before(todayDate)
            }
            return false
        }catch (e : Exception){
            e.printStackTrace()
            return  false
        }

    }


    fun beforeToday(dateString: String): Boolean {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        // Parse the given date
        val givenDate = dateFormat.parse(dateString) ?: return false

        // Get today's date
        val todayDate = Date()

        // Compare the dates
        return givenDate.before(todayDate)
    }

    fun afterToday(dateString: String): Boolean {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        // Parse the given date
        val givenDate = dateFormat.parse(dateString) ?: return false

        // Get today's date
        val todayDate = Date()

        // Compare the dates
        return givenDate.after(todayDate)
    }







    fun dayNameInBangla(dayOfWeek: Int) :String{
        var dayNameInBangla = ""
        when(dayOfWeek){
            1 -> {dayNameInBangla="রবিবার"}
            2 -> {dayNameInBangla="সোমবার"}
            3 -> {dayNameInBangla="মঙ্গলবার"}
            4 -> {dayNameInBangla="বুধবার"}
            5 -> {dayNameInBangla="বৃহস্পতিবার"}
            6 -> {dayNameInBangla="শুক্রবার"}
            7 -> {dayNameInBangla="শনিবার"}
            else-> {
                dayNameInBangla=""
            }
        }
        return  dayNameInBangla

    }
}