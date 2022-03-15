package com.hd.movie.widgets

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val span: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.apply {
            top = span
            left = span / 2
            right = span / 2
        }
//        for (i in 0..count) {
//            when {
//                position % count == 0 -> {
//                    outRect.apply {
//                        right = span / count
//                    }
//                }
//                position % count == count - 1 -> {
//                    outRect.apply {
//                        left = span / count
//                        right = span
//                    }
//                }
//                else -> {
//                    outRect.apply {
//                        left = span / count * (count - 1)
//                        right = span / count * (count - 1)
//                    }
//                }
//            }
//        }
    }
}