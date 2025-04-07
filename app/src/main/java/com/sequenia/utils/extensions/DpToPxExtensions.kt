package com.sequenia.utils.extensions

import android.content.Context

/**
 * Расширение для [Float], конвертирующее значения из `dp` в пиксели.
 *
 * @param context Контекст для доступа к метрикам экрана.
 * @return Значение в пикселях.
 */
fun Float.dpToPx(context: Context): Float =
    this * context.resources.displayMetrics.density
