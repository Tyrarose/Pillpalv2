package com.codingwithme.meowbottomnavigationbar

data class RecyclerViewList(
    var image: Int,
    var text: String,
    val times: List<String> // Change 'time' to 'times' and make it a list
)
