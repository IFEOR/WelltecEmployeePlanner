package com.ifeor.welltecemployeeplanner.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ifeor.welltecemployeeplanner.R

fun ImageView.setImagePlaceholder(img: String?) {
    val options = RequestOptions()
        .placeholder(R.drawable.no_photo)
        .circleCrop()
        .error(R.mipmap.ic_no_photo_round)
        .circleCrop()
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(img)
        .into(this)
}
