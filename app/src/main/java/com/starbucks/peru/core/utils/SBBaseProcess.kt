package com.starbucks.peru.core.utils

import android.content.Context

import java.util.*
import java.util.concurrent.TimeUnit

open class SBBaseProcess {

    internal fun getServerCurrentTimeStamp(context: Context): String {
        val dateServerStorage = GHServiceConfig.getDateServer(
            context = context
        )

        if (dateServerStorage == null) {
            val tsLong = System.currentTimeMillis() / 1000
            return tsLong.toString()
        }

        val currentDate = Date()
        val dateDeviceStorage = GHServiceConfig.getDateDevice(
            context = context
        ) ?: currentDate

        val completed = currentDate.time - dateDeviceStorage.time
        val seconds: Long = TimeUnit.SECONDS.convert(completed, TimeUnit.MILLISECONDS)

        if (seconds < 0) {
            val tsLong = dateServerStorage.time / 1000
            return tsLong.toString()
        }

        val calendar = Calendar.getInstance()
        calendar.time = dateServerStorage
        calendar.add(Calendar.SECOND, seconds.toInt())
        val tsLong = calendar.time.time / 1000

        return tsLong.toString()
    }


}