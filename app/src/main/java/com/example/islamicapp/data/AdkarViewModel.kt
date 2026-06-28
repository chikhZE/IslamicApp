package com.example.islamicapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

class AdkarViewModel(application: Application): AndroidViewModel(application) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val context = getApplication<Application>().applicationContext
                val jsonString = context.assets.open("azkar.json").bufferedReader().use { it.readText() }
                val jsonObject = JSONObject(jsonString)
                val keys = jsonObject.keys()
                val tempList = mutableListOf<String>()
                while (keys.hasNext()) {
                    tempList.add(keys.next())
                }
                _categories.value = tempList
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
    init {
        loadCategories()
    }
}