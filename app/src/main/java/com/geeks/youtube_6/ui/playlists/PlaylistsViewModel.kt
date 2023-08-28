package com.geeks.youtube_6.ui.playlists

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geeks.youtube_6.core.base.BaseViewModel
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.data.repository.Repository

class PlaylistsViewModel : BaseViewModel() {

    private val repository = Repository()

//    private var _playlists: MutableLiveData<PlaylistsModel> = MutableLiveData<PlaylistsModel>()
//    val playlists: LiveData<PlaylistsModel> = _playlists

    fun getPlayList(): LiveData<Resource<PlaylistsModel>> {
        Log.e("kiber",repository.getPlaylist().value.toString())
//        _playlists = repository.getPlaylist() as MutableLiveData<PlaylistsModel>
          return repository.getPlaylist()
    }
}