package com.task.newsapptask.app

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.task.newsapptask.R

class BindingAdapter {
    companion object {
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String?) {
            if (url.isNullOrEmpty()) {
                imageView.setImageResource(R.drawable.placeholder)
                return
            }

            val progressDrawable =
                CircularProgressDrawable(imageView.context)
            progressDrawable.strokeWidth = 5f
            progressDrawable.centerRadius = 30f
            progressDrawable.start()
            Glide.with(imageView.context)
                .load(url)
                .placeholder(progressDrawable)
                .fallback(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView)

        }
    }
}