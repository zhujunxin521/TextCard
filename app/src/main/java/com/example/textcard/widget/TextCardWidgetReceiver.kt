package com.example.textcard.widget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TextCardWidgetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        TextCardWidget().receive(context, intent)
    }
}