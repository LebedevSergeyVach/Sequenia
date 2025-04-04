package com.sequenia.ui.offset

import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.RecyclerView

import com.sequenia.adapter.main.MainAdapter

class FilmItemOffsetDecoration(
    private val spacing: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        val adapter: MainAdapter = parent.adapter as? MainAdapter ?: return

        when (adapter.getItemViewType(position)) {
            MainAdapter.Companion.TYPE_FILM -> {
                outRect.left = spacing / 2
                outRect.right = spacing / 2
                outRect.bottom = spacing
                outRect.top = spacing
            }

            MainAdapter.Companion.TYPE_HEADER -> {
                outRect.left = 0
                outRect.right = 0
                outRect.bottom = 0
                outRect.top = if (position == 0) spacing else spacing * 2
            }

            else -> outRect.set(0, 0, 0, 0)
        }
    }
}