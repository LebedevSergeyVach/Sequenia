package com.sequenia.fragments.films

import android.graphics.drawable.Drawable
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.sequenia.R
import com.sequenia.databinding.FragmentInfoFilmBinding

import java.util.Locale

/**
 * Фрагмент для отображения детальной информации о фильме.
 *
 * @see [Fragment] Базовый класс фрагмента.
 */
class InfoFilmFragment : Fragment() {

    companion object {
        const val LOCALIZED_NAME = "LOCALIZED_NAME"
        const val NAME = "NAME"
        const val YEAR = "YEAR"
        const val RATING = "RATING"
        const val IMAGE_URL = "IMAGE_URL"
        const val DESCRIPTION = "DESCRIPTION"
        const val GENRES = "GENRES"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentInfoFilmBinding.inflate(inflater, container, false)

        val localizedName: String = arguments?.getString(LOCALIZED_NAME) ?: ""
        val name: String = arguments?.getString(NAME) ?: ""
        val year: Int = arguments?.getInt(YEAR) ?: 0
        val rating: Float = arguments?.getFloat(RATING) ?: 0F
        val imageUrl: String = arguments?.getString(IMAGE_URL) ?: ""
        val description: String = arguments?.getString(DESCRIPTION) ?: ""
        val genres: List<String> = arguments?.getStringArrayList(GENRES) ?: emptyList()

        binding.textNameFilm.text = localizedName
        binding.textGenreListAndReleaseYear.text = buildString {
            append(genres.joinToString(" "))
            if (genres.isNotEmpty()) {
                append(", ")
            }
            append(year)
        }
        binding.textRating.text = String.format(Locale.US, "%.1f", rating)
        binding.textDescription.text = description

        renderingImageAttachmentPoster(binding = binding, imageUrl = imageUrl)

        controlToolbar(name = name)

        return binding.root
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
     * @param binding [FragmentInfoFilmBinding] фрагмента.
     * @param imageUrl `URL` изображения.
     *
     * @see [Glide] Для загрузки изображений
     */
    private fun renderingImageAttachmentPoster(
        binding: FragmentInfoFilmBinding,
        imageUrl: String
    ) {
        binding.skeletonAttachment.showSkeleton()

        Glide.with(binding.root)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .error(R.drawable.placeholder_films)
            .placeholder(R.drawable.placeholder_films)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .thumbnail(
                Glide.with(binding.root)
                    .load(imageUrl)
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

    /**
     * Настраивает [Toolbar] активности.
     *
     * @param name Название фильма для отображения в [Toolbar].
     */
    private fun controlToolbar(name: String) {
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = name
    }
}
