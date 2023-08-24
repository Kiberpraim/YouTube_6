package com.geeks.youtube_6.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ItemPlaylistsBinding

class PlaylistsAdapter : Adapter<PlaylistsAdapter.PlaylistViewHolder>() {

    private var _list = mutableListOf<PlaylistsModel.Item>()

    fun setListModel(playlistsModel: PlaylistsModel) {
        _list = playlistsModel.items.toMutableList()
        notifyDataSetChanged()
    }

    inner class PlaylistViewHolder(private val binding : ItemPlaylistsBinding) : ViewHolder(binding.root) {
        fun onBind(playlistsModelItem: PlaylistsModel.Item) {
            with(binding){
                tvPlaylistName.text = playlistsModelItem.snippet.title // Название плейлиста
                ivPlaylist.load(playlistsModelItem.snippet.thumbnails.default.url) // Ссылка на изображение по умолчанию
                tvNumberOfVideos.text = "${playlistsModelItem.contentDetails.itemCount} video series" // Количество видео в плейлисте
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = _list.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(_list[position])
    }
}