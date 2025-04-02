package com.sequenia.fragments.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sequenia.R
import com.sequenia.adapter.FilmAdapter
import com.sequenia.data.FilmData
import com.sequenia.databinding.FragmentFilmsBinding
import com.sequenia.ui.OffsetDecoration
import com.sequenia.utils.extensions.ErrorUtils.getErrorText
import com.sequenia.utils.extensions.singleVibrationWithSystemCheck
import com.sequenia.viewmodel.FilmsState
import com.sequenia.viewmodel.FilmsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment : Fragment() {

    private val viewModelFilms: FilmsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilmsBinding.inflate(inflater, container, false)

        val adapter = FilmAdapter(
            listener = object : FilmAdapter.FilmListener {
                override fun onGetInfoFilmClicked(film: FilmData) {
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
            }
        )

        binding.filmsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing_card_films)
            binding.filmsRecyclerView.addItemDecoration(OffsetDecoration(spacing))
        }
        binding.filmsRecyclerView.adapter = adapter

        binding.swiperRefresh.setOnRefreshListener {
            viewModelFilms.loadFilms()
            requireContext().singleVibrationWithSystemCheck(35)
        }

        binding.swiperRefresh.setColorSchemeColors(
            ContextCompat.getColor(requireContext(), R.color.swipe_refresh)
        )

        binding.swiperRefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(requireContext(), R.color.background_color_of_the_refresh_circle)
        )

        viewModelFilms.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { stateFilms: FilmsState ->
                binding.swiperRefresh.isRefreshing = stateFilms.isRefreshing
                binding.progressBar.isVisible = stateFilms.isEmptyLoading

                val errorText: CharSequence? =
                    stateFilms.statusFilmsState.throwableOrNull?.getErrorText(requireContext())

                errorText?.let {
                    Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
                    viewModelFilms.consumerError()
                }

                adapter.submitList(
                    stateFilms.films
                )
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }
}
