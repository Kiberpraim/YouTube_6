package com.geeks.youtube_6.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.geeks.youtube_6.core.network.RemoteDataSource
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.paging.PlaylistsPagingSource
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.data.paging.DetailsPagingSource
import kotlinx.coroutines.Dispatchers

class Repository(private val remoteDataSource: RemoteDataSource) {

    fun getPlaylist(): LiveData<PagingData<PlaylistsModel.Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10,),
            pagingSourceFactory = { PlaylistsPagingSource(remoteDataSource = remoteDataSource) }
        ).liveData
    }

    fun getDetails(playlistId: String): LiveData<PagingData<PlaylistsModel.Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10,),
            pagingSourceFactory = { DetailsPagingSource(
                remoteDataSource = remoteDataSource,
                playlistId = playlistId) }
        ).liveData
    }
}