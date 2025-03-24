package ru.vafeen.hwonlesson3.noui.providers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import ru.vafeen.hwonlesson3.main.MainActivity
import ru.vafeen.hwonlesson3.noui.keys.Actions
import ru.vafeen.hwonlesson3.ui.services.NotificationService

class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Actions.SURF_ACTION.action && context != null) {

            MainActivity.message = intent.getStringExtra("message")

            Log.e("App", "receiver сработал")

            Toast.makeText(context, "message = ${MainActivity.message}", Toast.LENGTH_LONG).show()

            NotificationService(context = context).showNotification(
                title = "secret:key",
                text = "message = ${MainActivity.message}"
            )


        }
    }

}
