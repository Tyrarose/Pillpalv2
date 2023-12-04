package com.codingwithme.meowbottomnavigationbar

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class RecyclerViewList(
    val image: Int,
    val text: String,
    val subTitle: String,
    val times: List<String>,
    val selectedNumber: String,
    val selectedDuration: String,
    val selectedDosage: String,
    val dosageAmount: String
) : Parcelable
