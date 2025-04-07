package com.sequenia.adapter.main

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.sequenia.BuildConfig
import com.sequenia.adapter.common.film.FilmViewHolder
import com.sequenia.adapter.common.genre.GenreViewHolder
import com.sequenia.adapter.common.header.HeaderViewHolder
import com.sequenia.data.FilmData
import com.sequenia.databinding.CardFilmBinding
import com.sequenia.databinding.CardGenreBinding
import com.sequenia.databinding.ItemHeaderSeparatorBinding
import com.sequenia.utils.helper.LoggerHelper

/**
 * Адаптер для отображения списка элементов главного экрана.
 * Поддерживает три типа элементов: фильмы, жанры и заголовки.
 *
 * @param listener Интерфейс для обработки пользовательских действий.
 *
 * @see [ListAdapter] Базовый класс адаптера.
 * @see [MainScreenItemSealed] Типы элементов списка.
 */
class MainAdapter(
    private val listener: FilmListener
) : ListAdapter<MainScreenItemSealed, ViewHolder>(MainItemCallback()) {

    companion object {
        const val TYPE_FILM = 0
        const val TYPE_GENRE = 1
        const val TYPE_HEADER = 2
    }

    interface FilmListener {
        fun onGetInfoFilmClicked(film: FilmData)
        fun onGenreClicked(genre: String)
    }

    /**
     * Определяет тип элемента списка по позиции.
     *
     * @param position Позиция элемента в списке.
     *
     * @return Одна из констант [TYPE_FILM], [TYPE_GENRE] или [TYPE_HEADER].
     */
    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is MainScreenItemSealed.Film -> TYPE_FILM
            is MainScreenItemSealed.Genre -> TYPE_GENRE
            is MainScreenItemSealed.Header -> TYPE_HEADER

            else -> {
                val error = "Invalid position view type: $position"
                if (BuildConfig.DEBUG) LoggerHelper.e(error)
                throw IllegalArgumentException(error)
            }
        }

    /**
     * Создает [ViewHolder] в зависимости от типа элемента.
     *
     * @param parent Родительская [ViewGroup].
     * @param viewType Тип создаваемого [ViewHolder].
     *
     * @return Созданный [ViewHolder].
     *
     * @see [FilmViewHolder]
     * @see [GenreViewHolder]
     * @see [HeaderViewHolder]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            TYPE_FILM -> createFilmViewHolder(parent = parent)
            TYPE_GENRE -> createGenreViewHolder(parent = parent)
            TYPE_HEADER -> createItemHeaderViewHolder(parent = parent)

            else -> {
                val error = "Invalid view type: $viewType"
                if (BuildConfig.DEBUG) LoggerHelper.e(error)
                throw IllegalArgumentException(error)
            }
        }

    /**
     * Привязывает данные к [ViewHolder].
     *
     * @param holder [ViewHolder] к которому привязываются данные.
     * @param position Позиция элемента в списке.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item: MainScreenItemSealed = getItem(position)) {
            is MainScreenItemSealed.Film -> (holder as FilmViewHolder).bind(film = item.data)

            is MainScreenItemSealed.Genre -> (holder as GenreViewHolder).bind(
                textGenre = item.name,
                isSelected = item.isSelected
            )

            is MainScreenItemSealed.Header -> (holder as HeaderViewHolder).bind(textHeader = item.title)
        }
    }

    /**
     * Создает [ViewHolder] для элемента типа [TYPE_FILM].
     *
     * @param parent Родительская [ViewGroup].
     *
     * @return Созданный [FilmViewHolder].
     */
    private fun createFilmViewHolder(parent: ViewGroup): FilmViewHolder {
        val binding =
            CardFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = FilmViewHolder(binding = binding)

        binding.cardFilm.setOnClickListener {
            val item =
                getItem(viewHolder.bindingAdapterPosition) as? MainScreenItemSealed.Film

            item?.data?.let(listener::onGetInfoFilmClicked)
        }

        return viewHolder
    }

    /**
     * Создает [ViewHolder] для элемента типа [TYPE_GENRE].
     *
     * @param parent Родительская [ViewGroup]
     *
     * @return Созданный [GenreViewHolder].
     */
    private fun createGenreViewHolder(parent: ViewGroup): GenreViewHolder {
        val binding =
            CardGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = GenreViewHolder(binding = binding)

        binding.root.setOnClickListener {
            val item =
                getItem(viewHolder.bindingAdapterPosition) as? MainScreenItemSealed.Genre

            item?.name?.let(listener::onGenreClicked)
        }

        return viewHolder
    }

    /**
     * Создает [ViewHolder] для элемента типа [TYPE_HEADER]".
     *
     * @param parent Родительская [ViewGroup].
     *
     * @return Созданный [HeaderViewHolder].
     */
    private fun createItemHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        val binding =
            ItemHeaderSeparatorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return HeaderViewHolder(binding = binding)
    }
}
