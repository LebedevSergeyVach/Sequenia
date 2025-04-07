package com.sequenia.fragments.films

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.os.bundleOf
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment

import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope

import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sequenia.BuildConfig
import com.sequenia.R
import com.sequenia.adapter.main.MainAdapter
import com.sequenia.data.FilmData
import com.sequenia.databinding.FragmentFilmsBinding
import com.sequenia.ui.mapper.MainScreenMapperImpl
import com.sequenia.ui.offset.FilmItemOffsetDecoration
import com.sequenia.utils.extensions.ErrorUtils.getErrorText
import com.sequenia.utils.extensions.showErrorMaterialSnackbar
import com.sequenia.utils.extensions.singleVibrationWithSystemCheck
import com.sequenia.utils.helper.LoggerHelper
import com.sequenia.viewmodel.FilmsState
import com.sequenia.viewmodel.FilmsViewModel

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Фрагмент для отображения списка фильмов.
 * Использует [FilmsViewModel] для управления состоянием и [RecyclerView] для отображения.
 *
 * @property viewModelFilms [viewModel] для управления данными фильмов.
 *
 * @see [Fragment] Базовый класс фрагмента.
 */
class FilmsFragment : Fragment() {

    private val viewModelFilms: FilmsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilmsBinding.inflate(inflater, container, false)
        val adapter = createFilmAdapter()

        controlSettingsFilmsRecyclerView(binding = binding, adapter = adapter)
        observedStateViewModel(binding = binding, adapter = adapter)

        return binding.root
    }

    /**
     * Создает адаптер для [RecyclerView] с обработчиками кликов.
     *
     * @return Настроенный адаптер [MainAdapter].
     */
    private fun createFilmAdapter(): MainAdapter = MainAdapter(
        listener = object : MainAdapter.FilmListener {
            override fun onGetInfoFilmClicked(film: FilmData) {
                requireContext().singleVibrationWithSystemCheck(35)

                requireParentFragment().findNavController()
                    .navigate(
                        R.id.action_FilmsFragment_to_InfoFilmsFragment,
                        bundleOf(
                            InfoFilmFragment.NAME to film.name,
                            InfoFilmFragment.LOCALIZED_NAME to film.localizedName,
                            InfoFilmFragment.YEAR to film.year,
                            InfoFilmFragment.RATING to film.rating,
                            InfoFilmFragment.IMAGE_URL to film.imageUrl,
                            InfoFilmFragment.DESCRIPTION to film.description,
                            InfoFilmFragment.GENRES to film.genres,
                        ),
                        NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in_right)
                            .setExitAnim(R.anim.slide_out_left)
                            .setPopEnterAnim(R.anim.slide_in_left)
                            .setPopExitAnim(R.anim.slide_out_right)
                            .build()
                    )
            }

            override fun onGenreClicked(genre: String) {
                requireContext().singleVibrationWithSystemCheck(35)

                viewModelFilms.toggleGenre(genre)
            }
        }
    )

    /**
     * Настраивает [RecyclerView] с [GridLayoutManager] и декораторами.
     *
     * @param binding [FragmentFilmsBinding] фрагмента.
     * @param adapter адаптер [MainAdapter] для [RecyclerView].
     */
    private fun controlSettingsFilmsRecyclerView(
        binding: FragmentFilmsBinding,
        adapter: MainAdapter
    ) {
        binding.filmsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position == RecyclerView.NO_POSITION) return 1

                    return when (adapter.getItemViewType(position)) {
                        MainAdapter.TYPE_HEADER -> 2
                        MainAdapter.TYPE_GENRE -> 2
                        MainAdapter.TYPE_FILM -> 1

                        else -> {
                            val error =
                                "FilmsFragment.controlSettingsFilmsRecyclerView: Invalid view type ${
                                    adapter.getItemViewType(
                                        position
                                    )
                                }"
                            if (BuildConfig.DEBUG) LoggerHelper.e(error)
                            throw IllegalArgumentException(error)
                        }
                    }
                }
            }

            binding.filmsRecyclerView.addItemDecoration(
                FilmItemOffsetDecoration(
                    itemSpacing = resources.getDimensionPixelSize(R.dimen.item_padding_card_films_8dp),
                    edgePadding = resources.getDimensionPixelSize(R.dimen.item_edge_padding_recycler_view_films_16dp),
                )
            )
        }

        binding.filmsRecyclerView.adapter = adapter
    }

    /**
     * Подписывается на изменения состояния (`asStateFlow()`) [FilmsViewModel].
     * Обновляет `UI` в соответствии с текущим состоянием.
     *
     * @param binding [FragmentFilmsBinding] фрагмента.
     * @param adapter Адаптер [MainAdapter] для [RecyclerView].
     */
    private fun observedStateViewModel(
        binding: FragmentFilmsBinding,
        adapter: MainAdapter,
    ) {
        viewModelFilms.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { stateFilms: FilmsState ->
                binding.progressBar.isVisible = stateFilms.isEmptyLoading
                binding.filmsRecyclerView.isVisible =
                    !stateFilms.isEmptyError && !stateFilms.isEmptyLoading

                stateFilms.statusFilmsState.throwableOrNull?.let { error: Throwable ->
                    requireContext().showErrorMaterialSnackbar(
                        binding = binding,
                        message = error.getErrorText(requireContext()).toString(),
                        retryAction = {
                            requireContext().singleVibrationWithSystemCheck(35)

                            viewModelFilms.load()
                        },
                    )
                }

                adapter.submitList(
                    MainScreenMapperImpl.map(
                        genres = viewModelFilms.getAvailableGenres(),
                        films = stateFilms.films,
                        selectedGenre = stateFilms.genre,
                        genresTitle = getString(R.string.genres),
                        filmsTitle = getString(R.string.films)
                    )
                )
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
