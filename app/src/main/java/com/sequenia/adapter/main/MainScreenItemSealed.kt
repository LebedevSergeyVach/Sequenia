package com.sequenia.adapter.main

import com.sequenia.data.FilmData

/**
 * Sealed class, представляющий различные типы элементов главного экрана.
 * Используется для отображения разнородных данных в RecyclerView.
 *
 * @see [MainAdapter] для использования в адаптере списка.
 */
sealed class MainScreenItemSealed {

    /**
     * Класс, представляющий заголовок секции.
     *
     * @param title Текст заголовка для отображения.
     */
    data class Header(val title: String) : MainScreenItemSealed()

    /**
     * Класс, представляющий жанр фильма.
     *
     * @param name Название жанра.
     * @param isSelected Флаг, указывающий выбран ли жанр (по умолчанию false).
     */
    data class Genre(val name: String, val isSelected: Boolean = false) : MainScreenItemSealed()

    /**
     * Класс, представляющий фильм.
     *
     * @param data Данные фильма типа [FilmData].
     */
    data class Film(val data: FilmData) : MainScreenItemSealed()
}
