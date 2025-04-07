package com.sequenia.adapter.common.genre

import androidx.recyclerview.widget.DiffUtil
import com.sequenia.adapter.main.MainScreenItemSealed.Genre

/**
 * [GenreItemCallback] - реализация [DiffUtil.ItemCallback] для элементов типа [Genre].
 *
 * Сравнивает жанры по их названию.
 */
class GenreItemCallback : DiffUtil.ItemCallback<Genre>() {

    /**
     * Определяет, являются ли элементы одинаковыми (по идентификатору).
     *
     * @param oldItem Старый элемент
     * @param newItem Новый элемент
     * @return true если жанры имеют одинаковое название
     */
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.name == newItem.name
    }

    /**
     * Определяет, совпадает ли содержимое элементов.
     *
     * @param oldItem Старый элемент
     * @param newItem Новый элемент
     * @return true если жанры полностью идентичны
     */
    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}
