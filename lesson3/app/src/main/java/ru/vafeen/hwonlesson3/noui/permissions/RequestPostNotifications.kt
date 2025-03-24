package ru.vafeen.hwonlesson3.noui.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun requestPostNotifications(context: Context){
    val permissionStatus =
        ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS);

    if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
            0
        )
    }
}