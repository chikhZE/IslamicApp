package com.example.islamicapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension for DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemeManager(private val context: Context) {
    companion object {
        val THEME_KEY = stringPreferencesKey("theme_mode")
    }

    val themeModeFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[THEME_KEY] ?: "system"
    }

    suspend fun saveThemeMode(mode: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = mode
        }
    }
}