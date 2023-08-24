package com.geeks.youtube_6.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geeks.youtube_6.core.base.BaseViewModel
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.data.remote.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : BaseViewModel() {

    private var _playlists: MutableLiveData<PlaylistsModel> = MutableLiveData<PlaylistsModel>()
    val playlists: LiveData<PlaylistsModel> = _playlists

    fun getPlayList() {
        RetrofitService.api.getPlaylists().enqueue(
            object : Callback<PlaylistsModel> {
                override fun onResponse(
                    call: Call<PlaylistsModel>,
                    response: Response<PlaylistsModel>
                ) {
                    if (response.isSuccessful) {
                        _playlists.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<PlaylistsModel>, t: Throwable) {
                    Log.e("kiber", t.message.toString())
                }

            }
        )
    }
}