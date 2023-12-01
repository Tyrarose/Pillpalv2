package com.codingwithme.meowbottomnavigationbar

data class RecyclerViewList(
    var image: Int,
    var text: String,
    var subTitle: String, // Add this
    val times: List<String>, // Change 'time' to 'times' and make it a list
    val number: String, // Add this
    val duration: String, // Add this
    val dosage: String, // Add this
    val dosageAmount: String // Add this
)
