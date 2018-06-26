/*
 * MyFirebaseMessagingService Created with ♥ by Farhan Yuda Pahlevi on 12/27/17 12:57 PM
 * Copyright (c) 2017. All Rights Reserved.
 *
 * Last Modified 12/27/17 9:30 AM
 */
@file:Suppress("DEPRECATION")

package id.pahlevikun.easygroupmaker.composer.service


import android.app.ActivityManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.view.ui.MainActivity


/**
 * Created by farhan on 11/27/17.
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val REQUEST_CODE = 1
    private val NOTIFICATION_ID = 6578

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // TODO: Handle FCM messages here.
        Log.d("BACKFCM", "From: " + remoteMessage!!.data.toString())
        val title: String = remoteMessage!!.data["title"].toString()
        val message = remoteMessage.data["body"].toString()

        showNotifications(title, message)


    }

    private fun showNotifications(title: String, msg: String) {
        val i = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE,
                i, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this)
                .setContentText(msg)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }

    fun isBackgroundRunning(context: Context): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningProcesses = am.runningAppProcesses
        for (processInfo in runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (activeProcess in processInfo.pkgList) {
                    if (activeProcess == context.packageName) {
                        //If your app is the process in foreground, then it's not in running in background
                        return false
                    }
                }
            }
        }
        return true
    }
}
