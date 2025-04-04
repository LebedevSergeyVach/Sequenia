package com.sequenia.adapter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sequenia.adapter.common.header.HeaderViewHolder
import com.sequenia.adapter.common.film.FilmViewHolder
import com.sequenia.adapter.common.genre.GenreViewHolder
import com.sequenia.data.FilmData
import com.sequenia.databinding.CardFilmBinding
import com.sequenia.databinding.CardGenreBinding
import com.sequenia.databinding.ItemHeaderSeparatorBinding

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
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is MainScreenItem.Film -> TYPE_FILM
            is MainScreenItem.Genre -> TYPE_GENRE
            is MainScreenItem.Header -> TYPE_HEADER
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            TYPE_FILM -> createFilmViewHolder(parent = parent)
            TYPE_GENRE -> createGenreViewHolder(parent = parent)
            TYPE_HEADER -> createItemHeaderViewHolder(parent = parent)
            else -> {
                error("MainAdapter.onCreateViewHolder: Unknown viewType $viewType")
                throw IllegalArgumentException("Invalid view type: $viewType")
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item: MainScreenItem = getItem(position)) {
            is MainScreenItem.Film -> (holder as FilmViewHolder).bind(film = item.data)
            is MainScreenItem.Genre -> (holder as GenreViewHolder).bind(textGenre = item.name)
            is MainScreenItem.Header -> (holder as HeaderViewHolder).bind(textHeader = item.title)
        }
    }

    private fun createFilmViewHolder(parent: ViewGroup): FilmViewHolder {
        val binding = CardFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = FilmViewHolder(binding = binding)

        binding.cardFilm.setOnClickListener {
            val item =
                getItem(viewHolder.bindingAdapterPosition) as? MainScreenItem.Film

            item?.data?.let(listener::onGetInfoFilmClicked)
        }

        return viewHolder
    }

    private fun createGenreViewHolder(parent: ViewGroup): GenreViewHolder {
        val binding = CardGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = GenreViewHolder(binding = binding)

        return viewHolder
    }

    private fun createItemHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        val binding =
            ItemHeaderSeparatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = HeaderViewHolder(binding = binding)

        return viewHolder
    }
}
