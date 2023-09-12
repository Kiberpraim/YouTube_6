package com.geeks.youtube_6.ui.playlists.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ItemPlaylistsBinding

class PlaylistsAdapter(
    private val context: Context,
    private val onItemClick: (PlaylistsModel.Item) -> Unit,
) : PagingDataAdapter<PlaylistsModel.Item, PlaylistViewHolder>(PlaylistsDiffUtil) {

    private var playlists = mutableListOf<PlaylistsModel.Item>()

/*    fun setListModel(playlistsModelItem: List<PlaylistsModel.Item>?) {
        playlists = playlistsModelItem as MutableList<PlaylistsModel.Item>
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = playlists.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { playlistsItem ->
            holder.onBind(
                playlistsModelItem = playlistsItem,
                onItemClick = onItemClick,
                context = context,
            )
        }
    }
}