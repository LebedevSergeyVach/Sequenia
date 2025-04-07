package com.sequenia.adapter.common.header

import androidx.recyclerview.widget.DiffUtil
import com.sequenia.adapter.main.MainScreenItemSealed.Header

/**
 * [HeaderItemCallback] - реализация [DiffUtil.ItemCallback] для элементов типа [Header].
 *
 * Сравнивает заголовки по их текстовому содержимому.
 */
class HeaderItemCallback : DiffUtil.ItemCallback<Header>() {

    /**
     * Определяет, являются ли элементы одинаковыми (по идентификатору).
     *
     * @param oldItem Старый элемент
     * @param newItem Новый элемент
     * @return true если заголовки имеют одинаковый текст
     */
    override fun areItemsTheSame(oldItem: Header, newItem: Header): Boolean {
        return oldItem.title == newItem.title
    }

    /**
     * Определяет, совпадает ли содержимое элементов.
     *
     * @param oldItem Старый элемент
     * @param newItem Новый элемент
     * @return true если заголовки полностью идентичны
     */
    override fun areContentsTheSame(oldItem: Header, newItem: Header): Boolean {
        return oldItem == newItem
    }
}
