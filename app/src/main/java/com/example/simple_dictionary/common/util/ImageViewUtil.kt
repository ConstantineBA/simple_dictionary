package com.example.simple_dictionary.common.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(
    uri: String,
    @DrawableRes placeholderId: Int? = null,
    roundRadius: Int = 0,
) {
    Glide.with(context)
        .load(uri)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .apply {
            if (placeholderId != null) placeholder(placeholderId)
            if (roundRadius > 0) transform(RoundedCorners(roundRadius))
        }
        .apply(RequestOptions.noTransformation())
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}