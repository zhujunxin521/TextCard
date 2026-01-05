package com.example.textcard.ui

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textcard.data.TextCardData
import com.example.textcard.ui.theme.TextCardTheme

@Composable
fun EditScreen(
    textCardData: TextCardData,
    onSave: (TextCardData) -> Unit
) {
    var text by remember { mutableStateOf(textCardData.text) }
    var fontSize by remember { mutableStateOf(textCardData.fontSize) }
    var textColor by remember { mutableStateOf(Color(textCardData.textColor)) }
    var backgroundColor by remember { mutableStateOf(Color(textCardData.backgroundColor)) }
    var cornerRadius by remember { mutableStateOf(textCardData.cornerRadius) }
    var backgroundAlpha by remember { mutableStateOf(textCardData.backgroundAlpha) }
    var fontFamily by remember { mutableStateOf(textCardData.fontFamily) }
    
    val context = LocalContext.current
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "文字卡片设置",
            style = MaterialTheme.typography.headlineMedium
        )
        
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("文字内容") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )
        
        Text(text = "字号: ${fontSize.toInt()}sp")
        Slider(
            value = fontSize,
            onValueChange = { fontSize = it },
            valueRange = 12f..48f,
            modifier = Modifier.fillMaxWidth()
        )
        
        Text(text = "圆角半径: ${cornerRadius.toInt()}dp")
        Slider(
            value = cornerRadius,
            onValueChange = { cornerRadius = it },
            valueRange = 0f..32f,
            modifier = Modifier.fillMaxWidth()
        )
        
        Text(text = "背景透明度: ${(backgroundAlpha * 100).toInt()}%")
        Slider(
            value = backgroundAlpha,
            onValueChange = { backgroundAlpha = it },
            valueRange = 0.1f..1f,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "字体选择", style = MaterialTheme.typography.titleSmall)
        listOf("default", "zcool_kuaile").forEach { family ->
            Button(
                onClick = { fontFamily = family },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (family == "default") "默认字体" else "站酷快乐体")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "预览", style = MaterialTheme.typography.titleSmall)
        
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(cornerRadius.dp),
            color = backgroundColor.copy(alpha = backgroundAlpha)
        ) {
            Text(
                text = text,
                fontSize = fontSize.sp,
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                onSave(
                    TextCardData(
                        text = text,
                        fontSize = fontSize,
                        textColor = textColor.toArgb(),
                        backgroundColor = backgroundColor.toArgb(),
                        cornerRadius = cornerRadius,
                        backgroundAlpha = backgroundAlpha,
                        fontFamily = fontFamily
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "保存")
        }
    }
}