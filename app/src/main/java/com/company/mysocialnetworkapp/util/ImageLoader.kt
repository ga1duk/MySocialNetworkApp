package com.company.mysocialnetworkapp.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.annotation.RawRes
import com.bumptech.glide.Glide

object ImageLoader {

    fun loadImage(view: ImageView, path: String) {
        Glide.with(view)
            .load(path)
            .timeout(10_000)
            .into(view)
    }

    fun loadImage(view: ImageView, @RawRes @DrawableRes @Nullable resId: Int) {
        Glide.with(view)
            .load(resId)
            .timeout(10_000)
            .into(view)
    }

    fun loadRoundedImage(view: ImageView, path: String) {
        Glide.with(view)
            .load(path)
            .circleCrop()
            .timeout(10_000)
            .into(view)
    }

    fun loadRoundedImage(view: ImageView, @RawRes @DrawableRes @Nullable resId: Int) {
        Glide.with(view)
            .load(resId)
            .circleCrop()
            .timeout(10_000)
            .into(view)
    }
}