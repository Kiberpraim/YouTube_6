package com.geeks.youtube_6.data.remote

import com.geeks.youtube_6.data.model.PlaylistsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaylistsApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlaylistsModel>
}