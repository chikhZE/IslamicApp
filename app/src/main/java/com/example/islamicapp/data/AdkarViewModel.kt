package com.example.islamicapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.json.JSONObject

class AdkarViewModel(application: Application): AndroidViewModel(application) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories
    private val _allAdkarMap = MutableStateFlow<Map<String, List<dikrData>>>(emptyMap())
    val allAdkarMap: StateFlow<Map<String, List<dikrData>>> = _allAdkarMap

    private val _allHadithList = MutableStateFlow<List<hadithData>>(emptyList())
    val allHadithList: StateFlow<List<hadithData>> = _allHadithList

    fun getDikr() {
        viewModelScope.launch {
            try {
                val context = getApplication<Application>().applicationContext
                val jsonString = context.assets.open("azkar.json").bufferedReader().use { it.readText() }
                val jsonConfig = Json { ignoreUnknownKeys = true }
                val parsedMap = jsonConfig.decodeFromString<Map<String, List<dikrData>>>(jsonString)
                _allAdkarMap.value = parsedMap
                _categories.value = parsedMap.keys.toList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
    fun getHadith() {
        viewModelScope.launch {
            try {
                val context = getApplication<Application>().applicationContext
                val jsonString = context.assets.open("40_hadith_nawawi.json").bufferedReader().use { it.readText() }
                val jsonConfig = Json { ignoreUnknownKeys = true }

                val parsedList = jsonConfig.decodeFromString<List<hadithData>>(jsonString)
                _allHadithList.value = parsedList
            } catch (e: Exception) {
                e.printStackTrace()
            }



        }
    }
    init {
        getDikr()
        getHadith()

    }
}