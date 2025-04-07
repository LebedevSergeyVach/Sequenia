package com.sequenia.utils.extensions

import android.content.Context
import com.sequenia.R
import okio.IOException

/**
 * Утилитарные функции для обработки ошибок и получения пользовательских сообщений об ошибках.
 * Эти функции позволяют преобразовывать исключения в читаемые сообщения, которые можно отображать пользователю.
 */
object ErrorUtils {

    /**
     * Возвращает текстовое сообщение об ошибке на основе переданного исключения.
     * Используется для обработки общих ошибок, таких как проблемы с сетью или неизвестные ошибки.
     *
     * @param context Контекст приложения, необходимый для доступа к строковым ресурсам.
     *
     * @return Сообщение об ошибке в виде [CharSequence]. Возвращает строку "Сетевая ошибка" для [IOException]
     *         и "Неизвестная ошибка" для всех остальных исключений.
     *
     * @see IOException
     */
    fun Throwable.getErrorText(context: Context): CharSequence = when (this) {
        is IOException -> context.getString(R.string.network_error)
        else -> context.getString(R.string.unknown_error)
    }
}
