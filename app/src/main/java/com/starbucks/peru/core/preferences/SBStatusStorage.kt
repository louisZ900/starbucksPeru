package com.starbucks.peru.core.preferences

import android.content.Context
import android.content.SharedPreferences
import com.starbucks.peru.core.preferences.model.enum.SBStatusEnum

class SBStatusStorage (var context: Context) {
    private val SHARED_NAME = "sb-shared-flags"
    private val PREF_NAME = "status-app"

    fun getFlag(): SBStatusEnum {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        val status = sharedPref.getInt(PREF_NAME, 0)

        return if (status == 1) SBStatusEnum.ACTIVE else SBStatusEnum.INACTIVE
    }

    fun saveFlag(value: SBStatusEnum) {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(PREF_NAME, value.value)
        editor.apply()
    }
}
