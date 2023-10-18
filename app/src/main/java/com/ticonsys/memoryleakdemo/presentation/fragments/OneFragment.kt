package com.ticonsys.memoryleakdemo.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ticonsys.memoryleakdemo.R
import com.ticonsys.memoryleakdemo.databinding.FragmentOneBinding
import com.ticonsys.memoryleakdemo.presentation.activities.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OneFragment : Fragment(R.layout.fragment_one) {

    private val viewModel by activityViewModels<MainViewModel>()

    private val binding: FragmentOneBinding by viewBinding()

    private var videoAdapter: VideoAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        binding.srfHome.setOnRefreshListener {
            viewModel.getVideos()
        }

        viewModel.videos.observe(viewLifecycleOwner){ videos ->
            videos ?: return@observe
            binding.srfHome.isRefreshing = false
            videoAdapter?.differ?.submitList(videos)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        videoAdapter = null
    }

    private fun setupRecyclerViews() {
        videoAdapter = VideoAdapter()
        binding.rvVideos.apply {
            adapter = videoAdapter
        }
        videoAdapter?.setOnItemClickListener { _, item ->
            findNavController().navigate(
                R.id.twoFragment,
                bundleOf(
                    "video_id" to item.id
                )
            )
        }
    }
}