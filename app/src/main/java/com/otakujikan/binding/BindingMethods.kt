package com.otakujikan.binding


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.otakujikan.R


object BindingMethods {

    /**
     * Binding adapter to load an image from a URL into an ImageView.
     * If the URL is null, it will use a placeholder image.
     *
     * @param view The ImageView where the image will be loaded.
     * @param url The URL of the image to load.
     */
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(view)
    }
}