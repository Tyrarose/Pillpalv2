package com.codingwithme.meowbottomnavigationbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    // LiveData to hold some data
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data

    init {
        // Initialize data
        _data.value = "Initial data"
    }

    // Method to update data
    fun updateData(newData: String) {
        _data.value = newData
    }
}