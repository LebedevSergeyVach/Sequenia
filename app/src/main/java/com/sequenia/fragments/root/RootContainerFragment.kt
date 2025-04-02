package com.sequenia.fragments.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController

import com.sequenia.R
import com.sequenia.databinding.FragmentRootBinding

class RootContainerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRootBinding.inflate(inflater, container, false)

        val navController =
            requireNotNull(childFragmentManager.findFragmentById(R.id.toolbarContainer)).findNavController()

        binding.toolbar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.FilmsFragment) {
                binding.toolbar.navigationIcon = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_arrow_24
                )?.apply {
                    setTint(ContextCompat.getColor(requireContext(), R.color.white))
                }
            }
        }

        return binding.root
    }
}
