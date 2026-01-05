package com.example.textcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.textcard.data.TextCardRepository
import com.example.textcard.ui.EditScreen
import com.example.textcard.ui.theme.TextCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val repository = TextCardRepository.getInstance(applicationContext)
        
        setContent {
            val textCardData by repository.textCardData.collectAsState()
            
            TextCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditScreen(
                        textCardData = textCardData,
                        onSave = { newData ->
                            repository.updateTextCardData(newData)
                            finish()
                        }
                    )
                }
            }
        }
    }
}