package com.starbucks.peru.core.fragment

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.starbucks.peru.R

fun Fragment.showHomeTitle(@StringRes title: Int) {
    view?.findViewById<TextView>(R.id.textView_home_title)?.text =
        getString(title)
}