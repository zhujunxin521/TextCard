package com.example.textcard.data

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.example.textcard.widget.TextCardWidget
import kotlinx.coroutines.flow.Flow

class TextCardRepository private constructor(private val context: Context) {
    
    val textCardData: Flow<TextCardData> = TextCardPreferences.getTextCardData(context)
    
    suspend fun updateTextCardData(data: TextCardData) {
        TextCardPreferences.saveTextCardData(context, data)
        TextCardWidget.updateAll(context)
    }
    
    companion object {
        @Volatile
        private var instance: TextCardRepository? = null
        
        fun getInstance(context: Context): TextCardRepository {
            return instance ?: synchronized(this) {
                instance ?: TextCardRepository(context.applicationContext).also { instance = it }
            }
        }
    }
}