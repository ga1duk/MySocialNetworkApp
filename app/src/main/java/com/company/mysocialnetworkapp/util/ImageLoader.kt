package com.company.mysocialnetworkapp.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.annotation.RawRes
import com.bumptech.glide.Glide

object ImageLoader {
    fun load(
        view: ImageView,
        @Nullable path: String? = null,
        @RawRes @DrawableRes @Nullable resId: Int? = null
    ) {
        val imageSource = path ?: resId
        Glide.with(view)
            .load(imageSource)
            .circleCrop()
            .timeout(10_000)
            .into(view)
    }
}