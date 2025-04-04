package com.sequenia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sequenia.data.FilmData
import com.sequenia.databinding.CardFilmBinding

class FilmAdapter(
    private val listener: FilmListener,
) : ListAdapter<FilmData, FilmViewHolder>(FilmItemCallback()) {

    interface FilmListener {
        fun onGetInfoFilmClicked(film: FilmData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardFilmBinding.inflate(layoutInflater, parent, false)

        val viewHolder = FilmViewHolder(binding = binding)

        binding.cardFilm.setOnClickListener {
            listener.onGetInfoFilmClicked(getItem(viewHolder.absoluteAdapterPosition))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bindFilms(
            film = getItem(position)
        )
    }
}
