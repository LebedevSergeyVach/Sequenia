package com.sequenia.adapter.common.genre

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sequenia.R
import com.sequenia.databinding.CardGenreBinding

class GenreViewHolder(
    private val binding: CardGenreBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(textGenre: String, isSelected: Boolean) {
        binding.itemHeaderSeparator.text = textGenre
        binding.root.setBackgroundColor(
            if (isSelected) {
                ContextCompat.getColor(binding.root.context, R.color.background_card_genre)
            } else {
                Color.TRANSPARENT
            }
        )
    }
}