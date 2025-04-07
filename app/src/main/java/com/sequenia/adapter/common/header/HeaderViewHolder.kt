package com.sequenia.adapter.common.header

import androidx.recyclerview.widget.RecyclerView
import com.sequenia.databinding.ItemHeaderSeparatorBinding

/**
 * [HeaderViewHolder] - [RecyclerView.ViewHolder] для отображения заголовков секций.
 *
 * @property binding ViewBinding элемента заголовка
 *
 * @see ItemHeaderSeparatorBinding Для структуры разметки
 */
class HeaderViewHolder(
    private val binding: ItemHeaderSeparatorBinding,
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Привязывает текст заголовка к View элементу.
     *
     * @param textHeader Текст заголовка для отображения
     */
    fun bind(textHeader: String) {
        binding.itemHeaderSeparator.text = textHeader
    }
}