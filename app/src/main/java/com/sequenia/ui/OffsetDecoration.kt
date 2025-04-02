package com.sequenia.ui

import android.graphics.Rect
import android.view.View

import androidx.annotation.Px

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Декорация для добавления отступов между элементами RecyclerView.
 *
 * @param offset Отступ между элементами.
 *
 * @see RecyclerView.ItemDecoration Базовый класс для декораций элементов RecyclerView.
 */
class OffsetDecoration(
    @Px private val offset: Int,
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        outRect.left = offset / 2
        outRect.right = offset / 2
        outRect.bottom = offset
        outRect.top = offset
    }
}
