package com.sequenia.adapter.common.genre

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sequenia.R
import com.sequenia.databinding.CardGenreBinding

/**
 * [GenreViewHolder] - [RecyclerView.ViewHolder] для отображения жанра фильма.
 *
 * @property binding ViewBinding элемента жанра
 *
 * @see CardGenreBinding Для структуры разметки
 */
class GenreViewHolder(
    private val binding: CardGenreBinding,
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Привязывает данные жанра к View элементам.
     *
     * @param textGenre Название жанра для отображения
     * @param isSelected Флаг выбранного состояния для окраски фона
     */
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
