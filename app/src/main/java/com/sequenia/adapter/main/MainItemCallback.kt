package com.sequenia.adapter.main

import androidx.recyclerview.widget.DiffUtil

class MainItemCallback : DiffUtil.ItemCallback<MainScreenItem>() {
    override fun areItemsTheSame(oldItem: MainScreenItem, newItem: MainScreenItem): Boolean =
        when {
            oldItem is MainScreenItem.Header && newItem is MainScreenItem.Header ->
                oldItem.title == newItem.title
            oldItem is MainScreenItem.Genre && newItem is MainScreenItem.Genre ->
                oldItem.id == newItem.id
            oldItem is MainScreenItem.Film && newItem is MainScreenItem.Film ->
                oldItem.data.id == newItem.data.id

            else -> false
        }

    override fun areContentsTheSame(oldItem: MainScreenItem, newItem: MainScreenItem): Boolean =
        oldItem == newItem
}
