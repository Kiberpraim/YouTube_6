package com.geeks.youtube_6.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geeks.youtube_6.core.network.RemoteDataSource
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel

class Repository {

    private val remoteDataSource = RemoteDataSource()

    fun getPlaylist(): LiveData<Resource<PlaylistsModel>> {
        val data = MutableLiveData<Resource<PlaylistsModel>>()
        data.postValue(remoteDataSource.getPlaylists())
        Log.e("kiber", data.value?.data.toString())
        return data
    }
}