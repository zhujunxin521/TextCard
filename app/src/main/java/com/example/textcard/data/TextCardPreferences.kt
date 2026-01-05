package com.example.textcard.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.textCardDataStore: DataStore<Preferences> by preferencesDataStore(name = "text_card")

object TextCardPreferences {
    private val TEXT_KEY = stringPreferencesKey("text")
    private val FONT_SIZE_KEY = floatPreferencesKey("font_size")
    private val TEXT_COLOR_KEY = stringPreferencesKey("text_color")
    private val BACKGROUND_COLOR_KEY = stringPreferencesKey("background_color")
    private val CORNER_RADIUS_KEY = floatPreferencesKey("corner_radius")
    private val BACKGROUND_ALPHA_KEY = floatPreferencesKey("background_alpha")
    private val FONT_FAMILY_KEY = stringPreferencesKey("font_family")
    
    fun getTextCardData(context: Context): Flow<TextCardData> {
        return context.textCardDataStore.data.map { preferences ->
            TextCardData(
                text = preferences[TEXT_KEY] ?: "点击编辑文字",
                fontSize = preferences[FONT_SIZE_KEY] ?: 24f,
                textColor = preferences[TEXT_COLOR_KEY]?.toIntOrNull() ?: 0xFF000000.toInt(),
                backgroundColor = preferences[BACKGROUND_COLOR_KEY]?.toIntOrNull() ?: 0xFFFFFFFF.toInt(),
                cornerRadius = preferences[CORNER_RADIUS_KEY] ?: 16f,
                backgroundAlpha = preferences[BACKGROUND_ALPHA_KEY] ?: 0.8f,
                fontFamily = preferences[FONT_FAMILY_KEY] ?: "default"
            )
        }
    }
    
    suspend fun saveTextCardData(context: Context, data: TextCardData) {
        context.textCardDataStore.edit { preferences ->
            preferences[TEXT_KEY] = data.text
            preferences[FONT_SIZE_KEY] = data.fontSize
            preferences[TEXT_COLOR_KEY] = data.textColor.toString()
            preferences[BACKGROUND_COLOR_KEY] = data.backgroundColor.toString()
            preferences[CORNER_RADIUS_KEY] = data.cornerRadius
            preferences[BACKGROUND_ALPHA_KEY] = data.backgroundAlpha
            preferences[FONT_FAMILY_KEY] = data.fontFamily
        }
    }
}