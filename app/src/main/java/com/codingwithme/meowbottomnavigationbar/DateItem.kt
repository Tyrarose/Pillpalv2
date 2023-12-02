package com.codingwithme.meowbottomnavigationbar

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

data class DateItem(val date: String, val day: String, var isSelected: Boolean = false, var isToday: Boolean = false)

fun generateDatesForMonth(): List<DateItem> {
    val dates = mutableListOf<DateItem>()

    // Get the current date
    val currentDate = LocalDate.now()

    // Get the first and last day of the current month
    val firstDayOfMonth = currentDate.withDayOfMonth(1)
    val lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth())

    // Calculate the start date so that the first item is always a Sunday
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek
    val daysToGoBack = if (firstDayOfWeek == DayOfWeek.SUNDAY) 0 else firstDayOfWeek.value
    val startDate = firstDayOfMonth.minusDays(daysToGoBack.toLong())

    // Generate the dates for the current month starting from startDate
    var date = startDate
    while (!date.isAfter(lastDayOfMonth)) {
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()) // Get the day of the week as a three-letter string
        val isToday = date.isEqual(LocalDate.now()) // Check if the date is the current date
        val dateItem = DateItem(date.dayOfMonth.toString(), dayOfWeek, isToday = isToday)
        dates.add(dateItem)
        date = date.plusDays(1)
    }
    return dates
}


