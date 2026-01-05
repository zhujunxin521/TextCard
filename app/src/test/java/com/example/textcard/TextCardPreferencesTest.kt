package com.example.textcard

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.core.app.ApplicationProvider
import com.example.textcard.data.TextCardData
import com.example.textcard.data.TextCardPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

val Context.testDataStore: DataStore<Preferences> by preferencesDataStore(name = "test_text_card")

class TextCardPreferencesTest {
    
    private lateinit var context: Context
    
    @BeforeEach
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
    }
    
    @Test
    fun `获取默认数据应该返回默认值`() = runTest {
        val data = TextCardPreferences.getTextCardData(context).first()
        
        assertEquals("点击编辑文字", data.text)
        assertEquals(24f, data.fontSize)
        assertEquals(0xFF000000.toInt(), data.textColor)
        assertEquals(0xFFFFFFFF.toInt(), data.backgroundColor)
        assertEquals(16f, data.cornerRadius)
        assertEquals(0.8f, data.backgroundAlpha)
        assertEquals("default", data.fontFamily)
    }
    
    @Test
    fun `保存和读取数据应该一致`() = runTest {
        val testData = TextCardData(
            text = "测试文字",
            fontSize = 30f,
            textColor = 0xFFFF0000.toInt(),
            backgroundColor = 0xFF0000FF.toInt(),
            cornerRadius = 20f,
            backgroundAlpha = 0.9f,
            fontFamily = "zcool_kuaile"
        )
        
        TextCardPreferences.saveTextCardData(context, testData)
        val savedData = TextCardPreferences.getTextCardData(context).first()
        
        assertEquals(testData.text, savedData.text)
        assertEquals(testData.fontSize, savedData.fontSize)
        assertEquals(testData.textColor, savedData.textColor)
        assertEquals(testData.backgroundColor, savedData.backgroundColor)
        assertEquals(testData.cornerRadius, savedData.cornerRadius)
        assertEquals(testData.backgroundAlpha, savedData.backgroundAlpha)
        assertEquals(testData.fontFamily, savedData.fontFamily)
    }
}