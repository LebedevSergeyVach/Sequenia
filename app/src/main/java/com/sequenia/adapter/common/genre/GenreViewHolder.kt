package com.sequenia.adapter.common.genre

import androidx.recyclerview.widget.RecyclerView
import com.sequenia.databinding.CardGenreBinding

class GenreViewHolder(
    private val binding: CardGenreBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(textGenre: String) {
        binding.itemHeaderSeparator.text = textGenre
    }
}