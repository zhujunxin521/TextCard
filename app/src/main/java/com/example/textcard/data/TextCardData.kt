package com.example.textcard.data

data class TextCardData(
    val text: String = "点击编辑文字",
    val fontSize: Float = 24f,
    val textColor: Int = 0xFF000000.toInt(),
    val backgroundColor: Int = 0xFFFFFFFF.toInt(),
    val cornerRadius: Float = 16f,
    val backgroundAlpha: Float = 0.8f,
    val fontFamily: String = "default"
)