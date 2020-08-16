package com.ikran.movieinfo.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ikran.movieinfo.R

//class BindingAdapters {
//
//    companion object {
//
//        @JvmStatic
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView).apply {
        (if (imageUrl.startsWith("http", true))
            load(imageUrl).placeholder(R.drawable.place_holder_poster)
        else
            load(R.drawable.place_holder_poster)).into(imageView)
    }
}
//    }
//}