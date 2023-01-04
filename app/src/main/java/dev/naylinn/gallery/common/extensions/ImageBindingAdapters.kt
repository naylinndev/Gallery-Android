package dev.naylinn.gallery.common.extensions

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("image", "placeholder")
fun setImage(image: AppCompatImageView, url: String?, placeholder: Drawable) {

    if (!url.isNullOrEmpty()) {
        Glide.with(image.context).load(url)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(placeholder)
            .into(image)

    } else {
        image.setImageDrawable(placeholder)
    }
}
