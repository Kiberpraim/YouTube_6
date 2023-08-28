package com.geeks.youtube_6.data.remote

import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaylistsApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlaylistsModel>
}