package com.geeks.youtube_6.core.network

import com.geeks.youtube_6.BuildConfig
import com.geeks.youtube_6.core.base.BaseDataSource
import com.geeks.youtube_6.data.remote.YouTubeApiService
import com.geeks.youtube_6.utils.Constants

class RemoteDataSource(private val youTubeApiService: YouTubeApiService) : BaseDataSource() {

    suspend fun getPlaylists(nextPageToken: String) = getResult {
        youTubeApiService.getPlaylists(
            key = BuildConfig.API_KEY,
            pageToken = nextPageToken,
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            maxResults = 10,
        )
    }

    suspend fun getDetails(playlistId: String,nextPageToken: String) = getResult {
        youTubeApiService.getDetails(
            key = BuildConfig.API_KEY,
            pageToken = nextPageToken,
            part = Constants.PART,
            playlistId = playlistId,
            maxResults = 10,
        )
    }
}
