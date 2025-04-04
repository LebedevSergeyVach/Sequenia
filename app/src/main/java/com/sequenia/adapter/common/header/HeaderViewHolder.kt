package com.sequenia.adapter.common.header

import androidx.recyclerview.widget.RecyclerView
import com.sequenia.databinding.ItemHeaderSeparatorBinding

class HeaderViewHolder(
    private val binding: ItemHeaderSeparatorBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(textHeader: String) {
        binding.itemHeaderSeparator.text = textHeader
    }
}