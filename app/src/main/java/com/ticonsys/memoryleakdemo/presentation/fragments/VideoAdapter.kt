package com.ticonsys.memoryleakdemo.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.ticonsys.memoryleakdemo.databinding.SimpleVideoItemBinding
import com.ticonsys.memoryleakdemo.domain.models.Video
import javax.inject.Inject

class VideoAdapter @Inject constructor(
): BaseAdapter<Video, SimpleVideoItemBinding>() {
    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ) = SimpleVideoItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem

    }

    override fun onBindViewHolder(holder: BaseViewHolder<SimpleVideoItemBinding>, position: Int) {
        val video = differ.currentList[position]
        holder.binding.apply {
            tvTitle.text = video.name
            ivVideoImage.load(video.image)
            root.setOnClickListener { view ->
                listener?.let { click ->
                    click(view, video)
                }
            }
        }
    }
}