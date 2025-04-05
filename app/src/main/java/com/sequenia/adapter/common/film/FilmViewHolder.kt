package com.sequenia.adapter.common.film

import android.graphics.drawable.Drawable

import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.sequenia.R
import com.sequenia.data.film.FilmData
import com.sequenia.databinding.CardFilmBinding

class FilmViewHolder(
    private val binding: CardFilmBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(film: FilmData) {
        binding.textNameFilm.text = film.localizedName
        binding.textNameFilm.minHeight = binding.textNameFilm.paint.fontMetricsInt.let { paint ->
            paint.descent - paint.ascent + paint.leading
        } * 2

        renderingImageAttachmentPoster(film = film)
    }

    private fun renderingImageAttachmentPoster(film: FilmData) {
        binding.skeletonAttachment.showSkeleton()

        Glide.with(binding.root)
            .load(film.imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .error(R.drawable.placeholder_films)
            .placeholder(R.drawable.placeholder_films)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .thumbnail(
                Glide.with(binding.root)
                    .load(film.imageUrl)
                    .override(50, 50)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.skeletonAttachment.showOriginal()

                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.skeletonAttachment.showOriginal()

                    return false
                }
            })
            .into(binding.imageFilmAttachment)
    }
}