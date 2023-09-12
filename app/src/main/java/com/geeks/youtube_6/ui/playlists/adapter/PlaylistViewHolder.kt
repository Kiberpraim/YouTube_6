package com.geeks.youtube_6.ui.playlists.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geeks.youtube_6.R
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ItemPlaylistsBinding


class PlaylistViewHolder(
    private val binding: ItemPlaylistsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(
        playlistsModelItem: PlaylistsModel.Item,
        onItemClick: (PlaylistsModel.Item) -> Unit,
        context: Context,
    ) {
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
