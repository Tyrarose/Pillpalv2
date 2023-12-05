package com.codingwithme.meowbottomnavigationbar

data class DDI_data(
    val id: Long = 0
)
data class DrugList(
    val id: Long = 0,
    val drug1_name: String,
    val drug2_name: String,
)

data class Interaction(
    val id: Long = 0,
    val drug1_name: String,
    val drug2_name: String,
    val interaction_type: String,
)


data class deets(
    val interaction_type: String,
    val severity: String,
    val description: String,
    val extended_description: String,
)

data class Item(val name: String)


