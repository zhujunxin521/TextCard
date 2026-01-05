package com.example.textcard

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.textcard.data.TextCardData
import com.example.textcard.data.TextCardRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TextCardRepositoryTest {
    
    private lateinit var repository: TextCardRepository
    
    @BeforeEach
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        repository = TextCardRepository.getInstance(context)
    }
    
    @Test
    fun `获取默认数据应该返回默认值`() = runTest {
        val data = repository.textCardData.first()
        
        assertEquals("点击编辑文字", data.text)
        assertEquals(24f, data.fontSize)
        assertEquals(0xFF000000.toInt(), data.textColor)
        assertEquals(0xFFFFFFFF.toInt(), data.backgroundColor)
        assertEquals(16f, data.cornerRadius)
        assertEquals(0.8f, data.backgroundAlpha)
        assertEquals("default", data.fontFamily)
    }
    
    @Test
    fun `更新数据应该成功`() = runTest {
        val newData = TextCardData(
            text = "更新后的文字",
            fontSize = 36f,
            textColor = 0xFF00FF00.toInt(),
            backgroundColor = 0xFFFFFF00.toInt(),
            cornerRadius = 24f,
            backgroundAlpha = 0.95f,
            fontFamily = "zcool_kuaile"
        )
        
        repository.updateTextCardData(newData)
        val savedData = repository.textCardData.first()
        
        assertEquals(newData.text, savedData.text)
        assertEquals(newData.fontSize, savedData.fontSize)
        assertEquals(newData.textColor, savedData.textColor)
        assertEquals(newData.backgroundColor, savedData.backgroundColor)
        assertEquals(newData.cornerRadius, savedData.cornerRadius)
        assertEquals(newData.backgroundAlpha, savedData.backgroundAlpha)
        assertEquals(newData.fontFamily, savedData.fontFamily)
    }
}