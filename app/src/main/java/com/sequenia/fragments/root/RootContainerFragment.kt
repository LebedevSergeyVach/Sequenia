package com.sequenia.fragments.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sequenia.databinding.FragmentRootBinding

class RootContainerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRootBinding.inflate(inflater, container, false)

        return binding.root
    }
}
