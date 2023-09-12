package com.geeks.youtube_6.ui.playlists.adapter

import androidx.recyclerview.widget.DiffUtil.*
import com.geeks.youtube_6.data.model.PlaylistsModel

object PlaylistsDiffUtil : ItemCallback<PlaylistsModel.Item>() {
    override fun areItemsTheSame(
        oldItem: PlaylistsModel.Item,
        newItem: PlaylistsModel.Item
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PlaylistsModel.Item,
        newItem: PlaylistsModel.Item
    ): Boolean {
        return oldItem == newItem
    }
}