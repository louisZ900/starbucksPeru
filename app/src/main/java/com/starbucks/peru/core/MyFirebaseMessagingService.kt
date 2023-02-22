package com.starbucks.peru.core

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.starbucks.peru.core.utils.LogUtils


class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //remoteMessage.notification!!.body?.let { LogUtils().v("FIREBASE", it) }
        LogUtils().v("onMessageReceived", "$remoteMessage")
    }

    override fun onNewToken(token: String) {
        LogUtils().v("FIREBASE", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.

    }
}