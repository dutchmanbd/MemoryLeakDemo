package com.ticonsys.memoryleakdemo.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ticonsys.memoryleakdemo.R
import com.ticonsys.memoryleakdemo.databinding.FragmentTwoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TwoFragment : Fragment(R.layout.fragment_two) {

    private val args by navArgs<TwoFragmentArgs>()

    private val videoId by lazy {
        args.videoId
    }

    private val binding: FragmentTwoBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("TwoFragment", "LeakCanary onViewCreated: $videoId")

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}