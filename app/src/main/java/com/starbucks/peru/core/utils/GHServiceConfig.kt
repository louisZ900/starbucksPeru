package com.starbucks.peru.core.utils

import android.content.Context
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class GHServiceConfig {
    companion object {
        private const val ConfigInfoFileName = "GHGlobalServiceConfig-info.json"
        internal const val GLOBAL_DATE_SERVER_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z"
        internal const val GLOBAL_DATE_DEVICE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

        private const val DATE_DEVICE_KEY = "gh_date_device_key"
        private const val SHARED_PREFERENCE_NAME = "GHNetworkServicesSharedPreferences"
        private const val DATE_SERVER_KEY = "gh_date_server_key"

        fun getDateServerStr(context: Context): String {
            val sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )

            return sharedPreferences.getString(
                DATE_SERVER_KEY,
                ""
            ) ?: ""
        }

        fun getDateDeviceStr(context: Context): String {
            val sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )

            return sharedPreferences.getString(
                DATE_DEVICE_KEY,
                ""
            ) ?: ""
        }

        fun getDateServer(context: Context): Date? {
            val dateServerStr = getDateServerStr(context)

            try {
                if (dateServerStr.isNotEmpty()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val formatter = DateTimeFormatter.ofPattern(
                            GLOBAL_DATE_SERVER_FORMAT,
                            Locale.US // Locale.getDefault()
                        )

                        val ldt = LocalDateTime.parse(dateServerStr, formatter)
                        return Date.from(ldt.toInstant(ZoneOffset.UTC))
                    }
                    else {
                        val simpleDateFormat = SimpleDateFormat(
                            GLOBAL_DATE_SERVER_FORMAT,
                            Locale.US // Locale.getDefault()
                        )
                        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

                        return simpleDateFormat.parse(dateServerStr)
                    }
                }
            }
            catch (err: Throwable) {
                err.printStackTrace()
            }

            return null
        }

        fun getDateDevice(context: Context): Date? {
            val dateDeviceStr = getDateDeviceStr(context)

            if (dateDeviceStr.isNotEmpty()) {
                try {
                    val simpleDateFormat = SimpleDateFormat(
                        GLOBAL_DATE_DEVICE_FORMAT,
                        Locale.getDefault()
                    )
                    //simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

                    return simpleDateFormat.parse(dateDeviceStr)
                }
                catch (err: Throwable) {
                    err.printStackTrace()
                }
            }

            return null
        }
    }



}