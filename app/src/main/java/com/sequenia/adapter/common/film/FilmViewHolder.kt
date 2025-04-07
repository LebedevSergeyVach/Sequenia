package com.sequenia.adapter.common.film

import android.graphics.Paint
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
import com.sequenia.data.FilmData
import com.sequenia.databinding.CardFilmBinding

/**
 * [FilmViewHolder] - [RecyclerView.ViewHolder] для отображения карточки фильма.
 *
 * @property binding ViewBinding карточки фильма
 *
 * @see CardFilmBinding Для структуры разметки
 */
class FilmViewHolder(
    private val binding: CardFilmBinding,
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Привязывает данные фильма к View элементам.
     *
     * @param film Данные фильма для отображения
     */
    fun bind(film: FilmData) {
        binding.textNameFilm.text = film.localizedName
        binding.textNameFilm.minHeight =
            binding.textNameFilm.paint.fontMetricsInt.let { paint: Paint.FontMetricsInt ->
                paint.descent - paint.ascent + paint.leading
            } * 2

        renderingImageAttachmentPoster(film = film)
    }

    /**
     * Загружает и отображает постер фильма с обработкой состояний с использованием [Glide].
     *
     * - Показывает скелетон во время загрузки
     * - Использует крос-фейд анимацию (500 мс)
     * - Загружает уменьшенную копию как превью
     * - Кэширует изображения на диске
     * - Обрабатывает ошибки загрузки
     *
     * @param film Данные фильма, содержащие URL изображения
     *
     * @see [Glide] Для загрузки изображений
     * @see [RequestListener] Для обработки событий загрузки
     */
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
