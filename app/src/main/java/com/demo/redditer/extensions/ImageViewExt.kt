package com.demo.redditer.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.demo.redditer.R
import com.squareup.picasso.Picasso

fun ImageView.loadPostPreview(url: String?) {
    Picasso.get().load(url).placeholder(R.drawable.ic_image_placeholder).into(this)
}

fun ImageView.loadPostPlaceholder(@DrawableRes placeHolder: Int) {
    Picasso.get().load(placeHolder).into(this)
}