package com.sequenia.adapter.common.film

import androidx.recyclerview.widget.DiffUtil
import com.sequenia.adapter.main.MainScreenItemSealed.Film

/**
 * [FilmItemCallback] - реализация [DiffUtil.ItemCallback] для элементов типа [Film].
 *
 * Сравнивает фильмы по их уникальному идентификатору.
 */
class FilmItemCallback : DiffUtil.ItemCallback<Film>() {

    /**
     * Определяет, являются ли элементы одинаковыми (по идентификатору).
     *
     * @param oldItem Старый элемент
     * @param newItem Новый элемент
     * @return true если фильмы имеют одинаковый ID
     */
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.data.id == newItem.data.id
    }

    /**
     * Определяет, совпадает ли содержимое элементов.
     *
     * @param oldItem Старый элемент
     * @param newItem Новый элемент
     * @return true если фильмы полностью идентичны
     */
    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}
