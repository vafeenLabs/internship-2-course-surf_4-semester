package ru.vafeen.hwonlesson3.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import ru.vafeen.hwonlesson3.noui.keys.Actions
import ru.vafeen.hwonlesson3.noui.providers.MessageReceiver
import ru.vafeen.hwonlesson3.ui.services.NotificationService.Companion.CHANNEL_ID
import ru.vafeen.hwonlesson3.ui.services.NotificationService.Companion.CHANNEL_NAME

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ContextCompat.registerReceiver(
            this,
            MessageReceiver(),
            IntentFilter(Actions.SURF_ACTION.action),
            ContextCompat.RECEIVER_EXPORTED
        )

        Log.e(
            "App",
            "Вызвался метод App::onCreate()"
        )  //!! Ребят, не бейте за Log.e, у меня Log.d не работает почему-то..


        // Я забыл об этом и минут 20 не понимал, что не так...

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH // мой телефон отказывается работать с маловажными сообщениями:(
            )

            val notificationManager = getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }

    }

    override fun onTerminate() {
        super.onTerminate()
    }


}