package com.geeks.youtube_6.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geeks.youtube_6.core.network.RemoteDataSource
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.utils.Constants

class DetailsPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val playlistId: String,
) : PagingSource<String, PlaylistsModel.Item>() {
    override fun getRefreshKey(state: PagingState<String, PlaylistsModel.Item>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PlaylistsModel.Item> {
        try {
            val nextPageToken = params.key ?: Constants.EMPTY_STRING
            val response = remoteDataSource.getDetails(playlistId, nextPageToken)

            val items = when(response.status){
                Resource.Status.SUCCESS -> response.data!!.items
                else -> {
                    emptyList()
                }
            }

            val prevKey = null
            val nextKey = response.data?.nextPageToken

            return LoadResult.Page(
                data = items,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}