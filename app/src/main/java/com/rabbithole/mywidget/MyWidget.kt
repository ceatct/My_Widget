package com.rabbithole.mywidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class MyWidget : AppWidgetProvider() {
    private var updateTimer: Timer? = null

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        if (updateTimer == null) {
            updateTimer = Timer()
            updateTimer?.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    val views = RemoteViews(context.packageName, R.layout.my_widget)
                    val currentTime = SimpleDateFormat("HH:mm:ss").format(Date())
                    views.setTextViewText(R.id.timeTv, currentTime)

                    val widgetId = appWidgetIds[0]
                    appWidgetManager.updateAppWidget(widgetId, views)
                }
            }, 0, 1000) 
        }
    }

    override fun onDisabled(context: Context) {
        if (updateTimer != null) {
            updateTimer?.cancel()
            updateTimer = null
        }
    }
}