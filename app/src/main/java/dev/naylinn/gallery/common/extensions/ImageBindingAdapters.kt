package dev.naylinn.gallery.common.extensions

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.io.IOException

@BindingAdapter("image", "placeholder")
fun setImage(image: AppCompatImageView, url: String?, placeholder: Drawable) {

    if (!url.isNullOrEmpty()) {
        Glide.with(image.context).load(url)
            .fitCenter()
            .override(1000, 1000)
            .placeholder(placeholder)
            .into(image)

    } else {
        image.setImageDrawable(placeholder)
    }
}
