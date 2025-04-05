package com.sequenia.utils.extensions

import android.content.Context

fun Float.dpToPx(context: Context): Float =
    this * context.resources.displayMetrics.density
