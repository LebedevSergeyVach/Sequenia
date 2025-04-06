package com.sequenia.adapter.main

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.sequenia.BuildConfig
import com.sequenia.adapter.common.film.FilmViewHolder
import com.sequenia.adapter.common.genre.GenreViewHolder
import com.sequenia.adapter.common.header.HeaderViewHolder
import com.sequenia.data.film.FilmData
import com.sequenia.databinding.CardFilmBinding
import com.sequenia.databinding.CardGenreBinding
import com.sequenia.databinding.ItemHeaderSeparatorBinding
import com.sequenia.utils.helper.LoggerHelper

class MainAdapter(
    private val listener: FilmListener
) : ListAdapter<MainScreenItem, ViewHolder>(MainItemCallback()) {

    companion object {
        const val TYPE_FILM = 0
        const val TYPE_GENRE = 1
        const val TYPE_HEADER = 2
    }

    interface FilmListener {
        fun onGetInfoFilmClicked(film: FilmData)
        fun onGenreClicked(genre: String)
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is MainScreenItem.Film -> TYPE_FILM
            is MainScreenItem.Genre -> TYPE_GENRE
            is MainScreenItem.Header -> TYPE_HEADER
            else -> {
                val error = "Invalid position view type: $position"

                if (BuildConfig.DEBUG) LoggerHelper.e(error)

                error(error)
                throw IllegalArgumentException(error)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            TYPE_FILM -> createFilmViewHolder(parent = parent)
            TYPE_GENRE -> createGenreViewHolder(parent = parent)
            TYPE_HEADER -> createItemHeaderViewHolder(parent = parent)
            else -> {
                val error = "Invalid view type: $viewType"

                if (BuildConfig.DEBUG) LoggerHelper.e(error)

                error(error)
                throw IllegalArgumentException(error)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item: MainScreenItem = getItem(position)) {
            is MainScreenItem.Film -> (holder as FilmViewHolder).bind(film = item.data)

            is MainScreenItem.Genre -> (holder as GenreViewHolder).bind(
                textGenre = item.name,
                isSelected = item.isSelected
            )

            is MainScreenItem.Header -> (holder as HeaderViewHolder).bind(textHeader = item.title)
        }
    }

    private fun createFilmViewHolder(parent: ViewGroup): FilmViewHolder {
        val binding =
            CardFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = FilmViewHolder(binding = binding)

        binding.cardFilm.setOnClickListener {
            val item =
                getItem(viewHolder.bindingAdapterPosition) as? MainScreenItem.Film

            item?.data?.let(listener::onGetInfoFilmClicked)
        }

        return viewHolder
    }

    private fun createGenreViewHolder(parent: ViewGroup): GenreViewHolder {
        val binding =
            CardGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = GenreViewHolder(binding = binding)

        binding.root.setOnClickListener {
            val item =
                getItem(viewHolder.bindingAdapterPosition) as? MainScreenItem.Genre

            item?.name?.let(listener::onGenreClicked)
        }

        return viewHolder
    }

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
