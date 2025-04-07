package com.sequenia.adapter.main

import androidx.recyclerview.widget.DiffUtil

import com.sequenia.adapter.common.film.FilmItemCallback
import com.sequenia.adapter.common.genre.GenreItemCallback
import com.sequenia.adapter.common.header.HeaderItemCallback

import com.sequenia.adapter.main.MainScreenItemSealed.Film
import com.sequenia.adapter.main.MainScreenItemSealed.Genre
import com.sequenia.adapter.main.MainScreenItemSealed.Header

/**
 * [MainItemCallback] - композитный [DiffUtil.ItemCallback],
 * делегирующий сравнение специализированным реализациям для каждого типа элемента.
 *
 * @property headerCallback Callback для заголовков
 * @property genreCallback Callback для жанров
 * @property filmCallback Callback для фильмов
 */
class MainItemCallback(
    private val headerCallback: HeaderItemCallback = HeaderItemCallback(),
    private val genreCallback: GenreItemCallback = GenreItemCallback(),
    private val filmCallback: FilmItemCallback = FilmItemCallback()
) : DiffUtil.ItemCallback<MainScreenItemSealed>() {


    /**
     * Определяет, являются ли элементы одинаковыми (по типу и идентификатору).
     *
     * @param oldItem Старый элемент
     * @param newItem Новый элемент
     * @return true если элементы одного типа и имеют одинаковые идентификаторы
     */
    override fun areItemsTheSame(oldItem: MainScreenItemSealed, newItem: MainScreenItemSealed): Boolean {
        return when {
            oldItem is Header && newItem is Header -> headerCallback.areItemsTheSame(oldItem, newItem)
            oldItem is Genre && newItem is Genre -> genreCallback.areItemsTheSame(oldItem, newItem)
            oldItem is Film && newItem is Film -> filmCallback.areItemsTheSame(oldItem, newItem)
            else -> false
        }
    }

    /**
     * Определяет, совпадает ли содержимое элементов.
     *
     * @param oldItem Старый элемент
     * @param newItem Новый элемент
     * @return true если элементы полностью идентичны
     */
    override fun areContentsTheSame(oldItem: MainScreenItemSealed, newItem: MainScreenItemSealed): Boolean {
        return when {
            oldItem is Header && newItem is Header -> headerCallback.areContentsTheSame(oldItem, newItem)
            oldItem is Genre && newItem is Genre -> genreCallback.areContentsTheSame(oldItem, newItem)
            oldItem is Film && newItem is Film -> filmCallback.areContentsTheSame(oldItem, newItem)
            else -> false
        }
    }
}
