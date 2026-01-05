package com.example.textcard.widget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.updateAll
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.example.textcard.MainActivity
import com.example.textcard.data.TextCardData
import com.example.textcard.data.TextCardRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class TextCardWidget : GlanceAppWidget() {
    
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val repository = TextCardRepository.getInstance(context)
        val data = runBlocking { repository.textCardData.first() }
        
        provideContent {
            GlanceTheme {
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(
                            Color(data.backgroundColor)
                                .copy(alpha = data.backgroundAlpha)
                        )
                        .clickable(actionStartActivity<MainActivity>())
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = data.text,
                        style = TextStyle(
                            color = Color(data.textColor),
                            fontSize = data.fontSize.sp
                        )
                    )
                }
            }
        }
    }
}