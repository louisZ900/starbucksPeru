package com.starbucks.peru.core.utils

import android.view.WindowManager
import androidx.fragment.app.FragmentActivity

class SBBlockScreenshots {
    companion object{
        fun blockScreenshots(view : Any?){
            val fragment = view as FragmentActivity?
                fragment?.let {
                    it.window?.setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE)
                }
        }
    }
}