package com.geeks.youtube_6.ui.details

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.geeks.youtube_6.core.base.BaseViewModel
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.data.repository.Repository

class DetailsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getDetails(playlistId: String): LiveData<PagingData<PlaylistsModel.Item>> =
        repository.getDetails(playlistId)
}