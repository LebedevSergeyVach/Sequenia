package com.sequenia.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sequenia.data.FilmData

class FilmItemCallback : DiffUtil.ItemCallback<FilmData>() {
    override fun areItemsTheSame(oldItem: FilmData, newItem: FilmData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FilmData, newItem: FilmData): Boolean =
        oldItem == newItem
}
