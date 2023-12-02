package com.codingwithme.meowbottomnavigationbar

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

data class DateItem(val date: String, val day: String, var isSelected: Boolean = false)
fun generateDatesForMonth(): List<DateItem> {
    val dates = mutableListOf<DateItem>()

    // Get the current date
    val currentDate = LocalDate.now()

    // Get the first and last day of the current month
    val firstDayOfMonth = currentDate.withDayOfMonth(1)
    val lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth())

    // Generate the dates for the current month
    var date = firstDayOfMonth
    while (!date.isAfter(lastDayOfMonth)) {
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()) // Get the day of the week as a three-letter string
        val dateItem = DateItem(date.dayOfMonth.toString(), dayOfWeek)
        dates.add(dateItem)
        date = date.plusDays(1)
    }
    return dates
}
