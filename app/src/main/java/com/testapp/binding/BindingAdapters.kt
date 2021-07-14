package com.testapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapters {

    @BindingAdapter("loadSrc")
    fun ImageView.loadImage(src: String) {
        Glide.with(this.context).load(src).into(this)
    }

}