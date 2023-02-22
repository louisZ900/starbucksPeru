package com.starbucks.peru.core

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.salesforce.marketingcloud.messages.push.PushMessageManager
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk
import com.starbucks.peru.core.utils.LogUtils


class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        //remoteMessage.notification!!.body?.let { LogUtils().v("FIREBASE", it) }
        LogUtils().v("onMessageReceived", "$message")
        if (PushMessageManager.isMarketingCloudPush(message)) {
            SFMCSdk.requestSdk { sdk ->
                sdk.mp {
                    it.pushMessageManager.handleMessage(message)
                }
            }
        } else {
            // Not a push from the Marketing Cloud.  Handle manually.
        }
    }

    override fun onNewToken(token: String) {
        LogUtils().v("FIREBASE", "Refreshed token: $token")
        SFMCSdk.requestSdk { sdk ->
            sdk.mp {
                it.pushMessageManager.setPushToken(token)
            }
        }

    }
}