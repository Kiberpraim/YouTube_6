package com.geeks.youtube_6.ui.playlists

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geeks.youtube_6.R
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ItemPlaylistsBinding

class PlaylistsAdapter(
    private val context: Context,
    private val onItemClick: (PlaylistsModel.Item) -> Unit,
) :
    Adapter<PlaylistsAdapter.PlaylistViewHolder>() {

    private var _list = mutableListOf<PlaylistsModel.Item>()

    fun setListModel(playlistsModelItem: List<PlaylistsModel.Item>?) {
        _list = playlistsModelItem as MutableList<PlaylistsModel.Item>
        notifyDataSetChanged()
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistsBinding) :
        ViewHolder(binding.root) {
        fun onBind(playlistsModelItem: PlaylistsModel.Item) {
            with(binding) {
                tvPlaylistName.text = playlistsModelItem.snippet.title // Название плейлиста
                ivPlaylist.load(playlistsModelItem.snippet.thumbnails.default.url) // Ссылка на изображение по умолчанию
                if (playlistsModelItem.snippet.localized != null) {
                    tvNumberOfVideos.text =
                        playlistsModelItem.contentDetails.itemCount.toString() + context.getString(R.string.video_series) // Количество видео в плейлисте
                } else {
                    tvNumberOfVideos.text = context.getString(R.string._04_00)
                    tvInIvPlaylist.text = ""
                    tvInIvPlaylist.background.colorFilter = PorterDuffColorFilter(
                        Color.TRANSPARENT,
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
            itemView.setOnClickListener {
                onItemClick(
                    playlistsModelItem
                )
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