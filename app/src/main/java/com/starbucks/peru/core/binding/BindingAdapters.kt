package com.starbucks.peru.core.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.starbucks.peru.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        val poster = "$imageUrl"
        Glide.with(view.context)
            .load(poster)
            .placeholder(R.drawable.ic_default)
            .into(view)
    }
}