package com.sequenia.adapter.main

import androidx.recyclerview.widget.DiffUtil

import com.sequenia.adapter.main.MainScreenItem.Film
import com.sequenia.adapter.main.MainScreenItem.Genre
import com.sequenia.adapter.main.MainScreenItem.Header

class MainItemCallback : DiffUtil.ItemCallback<MainScreenItem>() {
    override fun areItemsTheSame(oldItem: MainScreenItem, newItem: MainScreenItem): Boolean {
        return when {
            oldItem is Header && newItem is Header -> oldItem.title == newItem.title
            oldItem is Genre && newItem is Genre -> oldItem.name == newItem.name
            oldItem is Film && newItem is Film -> oldItem.data.id == newItem.data.id
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: MainScreenItem, newItem: MainScreenItem): Boolean {
        return when {
            oldItem is Header && newItem is Header -> oldItem == newItem
            oldItem is Genre && newItem is Genre -> oldItem == newItem
            oldItem is Film && newItem is Film -> oldItem == newItem
            else -> false
        }
    }
}
