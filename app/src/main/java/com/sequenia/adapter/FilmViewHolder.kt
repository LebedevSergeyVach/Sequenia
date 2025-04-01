package com.sequenia.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sequenia.R
import com.sequenia.data.FilmData
import com.sequenia.databinding.CardFilmBinding

class FilmViewHolder(
    private val binding: CardFilmBinding,
) : ViewHolder(binding.root) {
    fun bindFilms(film: FilmData) {
        binding.textNameFilm.text = film.name

        renderingImageAttachmentPoster(film = film)
    }

    private fun renderingImageAttachmentPoster(film: FilmData) {
        Glide.with(binding.root)
            .load(film.imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_foreground)
            .thumbnail(
                Glide.with(binding.root)
                    .load(film.imageUrl)
                    .override(50, 50)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(binding.imageFilmAttachment)
    }
}
