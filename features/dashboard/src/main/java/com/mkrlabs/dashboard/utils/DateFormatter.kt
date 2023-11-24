package com.mkrlabs.dashboard.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateFormatter {

    fun getDayNameFromDate(dateString: String): String {
        try {
            //val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date = formatter.parse(dateString) ?: Date()

            val calendar = Calendar.getInstance()
            calendar.time = date

            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            val dayName = SimpleDateFormat("EEEE", Locale.getDefault()).format(date)

            return dayNameInBangla(dayOfWeek)
        }catch (e:Exception){
            e.printStackTrace()
            return ""
        }

    }


    fun examDateAndTodaysDateIsSame(examDate : String) : Boolean{
        try {
            val currentDate = Date()
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val today =  formatter.format(currentDate)
            return today.equals(examDate)
        }catch (e:Exception){
            return  false
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun isCurrentTimeAfterGivenTime(givenTime: String): Boolean {
        try {
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            // Parse the given time
            val parsedGivenTime = LocalTime.parse(givenTime, formatter)
            // Get the current time
            val currentTime = LocalTime.now()
            // Compare the two times
            return currentTime.isAfter(parsedGivenTime)
        }catch (e : Exception){
            return false
        }

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
        try {
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            // Parse the given date
            val givenDate = dateFormat.parse(dateString) ?: return false

            // Get today's date
            val todayDate = Date()

            // Compare the dates
            return givenDate.before(todayDate)
        }catch (e:Exception){
            e.printStackTrace()
            return false
        }

    }

    fun afterToday(dateString: String): Boolean {
        try {
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            // Parse the given date
            val givenDate = dateFormat.parse(dateString) ?: return false

            // Get today's date
            val todayDate = Date()

            // Compare the dates
            return givenDate.after(todayDate)
        }catch (e:Exception){
            return false
            e.printStackTrace()
        }

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