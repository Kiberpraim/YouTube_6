package com.geeks.youtube_6.ui.details

import androidx.lifecycle.LiveData
import com.geeks.youtube_6.core.base.BaseViewModel
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.data.repository.Repository

class DetailsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getDetails(playlistId: String): LiveData<Resource<PlaylistsModel>> = repository.getDetails(playlistId)
}