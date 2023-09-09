package com.geeks.youtube_6.ui.playlists

import androidx.lifecycle.LiveData
import com.geeks.youtube_6.core.base.BaseViewModel
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.data.repository.Repository

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlayList(): LiveData<Resource<PlaylistsModel>> = repository.getPlaylist()
}
