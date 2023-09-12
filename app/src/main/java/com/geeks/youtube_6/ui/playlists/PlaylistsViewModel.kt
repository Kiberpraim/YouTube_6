package com.geeks.youtube_6.ui.playlists

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.geeks.youtube_6.core.base.BaseViewModel
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.data.repository.Repository

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlayList(): LiveData<PagingData<PlaylistsModel.Item>> = repository.getPlaylist()
}
