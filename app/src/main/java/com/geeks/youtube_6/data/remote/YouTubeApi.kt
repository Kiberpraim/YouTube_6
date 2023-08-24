package com.geeks.youtube_6.data.remote

import com.geeks.youtube_6.BuildConfig
import com.geeks.youtube_6.data.model.PlaylistsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String = "contentDetails, snippet",
        @Query("channelId") channelId: String = "UCFiaumQAjtZwWlxdbgYP7vw",
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("maxResults") maxResults: Int = 10,
        ) : Call<PlaylistsModel>
}