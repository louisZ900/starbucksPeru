package com.starbucks.peru

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.salesforce.marketingcloud.MCLogListener
import com.salesforce.marketingcloud.MarketingCloudConfig
import com.salesforce.marketingcloud.MarketingCloudSdk
import com.salesforce.marketingcloud.messages.iam.InAppMessage
import com.salesforce.marketingcloud.messages.iam.InAppMessageManager
import com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions
import com.salesforce.marketingcloud.sfmcsdk.InitializationStatus
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogLevel
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogListener
import com.starbucks.peru.core.utils.LogUtils
import dagger.hilt.android.HiltAndroidApp

const val LOG_TAG = "~#MCLearningApp"

@HiltAndroidApp
class SBApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        // Initialize logging before initializing the SDK to avoid losing valuable debugging information.
        if (BuildConfig.DEBUG) {
            // Only log for DEBUG builds
            MarketingCloudSdk.setLogLevel(MCLogListener.VERBOSE)
            MarketingCloudSdk.setLogListener(MCLogListener.AndroidLogListener())
            SFMCSdk.requestSdk { sdk ->
                sdk.mp { push ->
                    push.registrationManager.registerForRegistrationEvents {
                        // Log the registration on successful sends to the MC
                        LogUtils().v(LOG_TAG, "Registration: $it")
                    }
                }
            }
        }

        // You MUST initialize the SDK in your Application's onCreate to ensure correct
        // functionality when the app is launched from a background service (receiving push message,
        // entering a geofence, ...)
        SFMCSdk.configure(applicationContext as Application, SFMCSdkModuleConfig.build {
            pushModuleConfig = MarketingCloudConfig.builder().apply {
                setApplicationId("6d2f10b0-b735-4a44-8578-c1ba353befca")
                setAccessToken("IdlMxr4b8nhazDBrK6ufR0Ue")
                setSenderId("starbucks-peru-test")
                setMarketingCloudServerUrl("https://mccw-lhb4r-4361d3wd335p6jddm.device.marketingcloudapis.com/")
                setNotificationCustomizationOptions(
                    NotificationCustomizationOptions.create(R.drawable.sb_logo_with_tm)
                )
                setInboxEnabled(true)
                setAnalyticsEnabled(true)
                setPiAnalyticsEnabled(true)
                setGeofencingEnabled(true)
                setProximityEnabled(true)
                // Other configuration options
            }.build(applicationContext)
        }) { initStatus ->
            // TODO handle initialization status
            when (initStatus.status) {
                InitializationStatus.SUCCESS -> {
                    LogUtils().v(LOG_TAG, "Marketing Cloud initialization successful.")
                }
                InitializationStatus.FAILURE -> {
                    // Given that this app is used to show SDK functionality we will hard exit if SDK init outright failed.
                    LogUtils().v(
                        LOG_TAG,
                        "Marketing Cloud initialization failed.  Exiting Learning App with exception."
                    )
                    throw RuntimeException("Init failed")

                }
            }
        }

        SFMCSdk.requestSdk { sdk ->
            sdk.mp {
                it.inAppMessageManager.run {

                    // Set the status bar color to be used when displaying an In App Message.
                    setStatusBarColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.colorPrimary
                        )
                    )
                    // Set the font to be used when an In App Message is rendered by the SDK
                    setTypeface(
                        ResourcesCompat.getFont(
                            applicationContext,
                            R.font.avenir_lts_td_heavy
                        )
                    )

                    setInAppMessageListener(object : InAppMessageManager.EventListener {
                        override fun shouldShowMessage(message: InAppMessage): Boolean {
                            // This method will be called before a in app message is presented.  You can return `false` to
                            // prevent the message from being displayed.  You can later use call `InAppMessageManager#showMessage`
                            // to display the message if the message is still on the device and active.
                            return true
                        }

                        override fun didShowMessage(message: InAppMessage) {
                            LogUtils().v(LOG_TAG, "${message.id} was displayed.")
                        }

                        override fun didCloseMessage(message: InAppMessage) {
                            LogUtils().v(LOG_TAG, "${message.id} was closed.")
                        }
                    })
                }
            }
        }
    }
}
