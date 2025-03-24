package ru.vafeen.hwonlesson3.ui.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import ru.vafeen.hwonlesson3.R
import ru.vafeen.hwonlesson3.main.MainActivity

class NotificationService(
    private val context: Context,
) {
    companion object {
        var NOTIFICATION_ID = 0
        const val CHANNEL_ID = "ru.vafeen.surf_dz_on_lesson3"
        const val CHANNEL_NAME = "Surf channel"
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val myIntent = Intent(context, MainActivity::class.java)

    private val pendingIntent = PendingIntent.getActivity(
        context, 0,
        myIntent,
        PendingIntent.FLAG_IMMUTABLE
    )

    fun showNotification(title: String, text: String) {

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
        NOTIFICATION_ID += 1
    }
}