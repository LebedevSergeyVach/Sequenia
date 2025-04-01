package com.sequenia.fragments.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sequenia.BuildConfig
import com.sequenia.adapter.FilmAdapter
import com.sequenia.databinding.FragmentFilmsBinding
import com.sequenia.utils.helper.LoggerHelper
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

        val adapter = FilmAdapter()

        binding.filmsRecyclerView.adapter = adapter

        viewModelFilms.state
            .flowWithLifecycle(lifecycle = viewLifecycleOwner.lifecycle)
            .onEach { stateFilms: FilmsState ->

                if (BuildConfig.DEBUG) LoggerHelper.d(stateFilms.films.toString())

                adapter.submitList(
                    stateFilms.films
                )
            }
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)

        return binding.root
    }
}
