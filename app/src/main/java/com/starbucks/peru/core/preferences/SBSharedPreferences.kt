package com.starbucks.peru.core.preferences

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


class SBSharedPreferences @Inject constructor(
    private val sbSharedPreferences: SharedPreferences
) {
    fun putValue(key: String, data: Boolean) = sbSharedPreferences.edit().putBoolean(key, data).apply()
    fun putValue(key: String, data: String) = sbSharedPreferences.edit().putString(key, data).apply()
    fun putValue(key: String, data: Int) = sbSharedPreferences.edit().putInt(key, data).apply()
    fun getBoolean(key: String) = sbSharedPreferences.getBoolean(key, false)
    fun getString(key: String) = sbSharedPreferences.getString(key, "")
    fun getInt(key: String) = sbSharedPreferences.getInt(key, -1)
    fun deleteString(data: String) = sbSharedPreferences.edit().remove(data).apply()
}