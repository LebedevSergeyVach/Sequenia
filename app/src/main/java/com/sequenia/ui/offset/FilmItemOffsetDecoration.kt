package com.sequenia.ui.offset

import android.graphics.Rect
import android.view.View

import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

import com.sequenia.adapter.main.MainAdapter

class FilmItemOffsetDecoration(
    @Px private val itemSpacing: Int,
    @Px private val edgePadding: Int = itemSpacing * 2,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) {
            outRect.set(0, 0, 0, 0)
            return
        }
        val adapter: MainAdapter = parent.adapter as? MainAdapter ?: return

        when (adapter.getItemViewType(position)) {
            MainAdapter.Companion.TYPE_FILM -> {
                val column = position % 2

                outRect.top = itemSpacing
                outRect.bottom = itemSpacing

                outRect.left = if (column == 1) edgePadding else itemSpacing / 2
                outRect.right = if (column == 0) edgePadding else itemSpacing / 2
            }

            MainAdapter.Companion.TYPE_HEADER -> {
                outRect.left = 0
                outRect.right = 0
                outRect.bottom = 0
                outRect.top = if (position == 0) itemSpacing else itemSpacing * 2
            }

            else -> outRect.set(0, 0, 0, 0)
        }
    }
}
