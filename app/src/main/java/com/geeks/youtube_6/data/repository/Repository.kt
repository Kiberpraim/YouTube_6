package com.geeks.youtube_6.data.repository

import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.geeks.youtube_6.core.network.RemoteDataSource
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel
import kotlinx.coroutines.Dispatchers

class Repository(private val remoteDataSource: RemoteDataSource) {

    fun getPlaylist(): LiveData<Resource<PlaylistsModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            emit(remoteDataSource.getPlaylists())
        }
    }

    fun getDetails(playlistId: String): LiveData<Resource<PlaylistsModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            emit(remoteDataSource.getDetails(playlistId))
        }
    }

}