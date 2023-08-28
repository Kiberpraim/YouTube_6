package com.geeks.youtube_6.core.network

import com.geeks.youtube_6.BuildConfig
import com.geeks.youtube_6.core.base.BaseDataSource
import com.geeks.youtube_6.utils.Constants

class RemoteDataSource : BaseDataSource() {

    private val playlistsApiService = RetrofitService.getApiService()

     fun getPlaylists() = getResult {
        playlistsApiService.getPlaylists(
            key = BuildConfig.API_KEY,
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            maxResults = 10,
        )
    }
}
