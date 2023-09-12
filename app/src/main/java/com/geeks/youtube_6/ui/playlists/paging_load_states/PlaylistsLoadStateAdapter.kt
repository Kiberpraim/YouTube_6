package com.geeks.youtube_6.ui.playlists.paging_load_states

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeks.youtube_6.R
import com.geeks.youtube_6.databinding.NoInternetConnectionBinding

class PlaylistsLoadStateAdapter(private val retry: () -> Unit):
    LoadStateAdapter<PlaylistsLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PlaylistsLoadStateViewHolder, loadState: LoadState) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PlaylistsLoadStateViewHolder {
        return PlaylistsLoadStateViewHolder.create(parent, retry)
    }
}

class PlaylistsLoadStateViewHolder(
    private val binding: NoInternetConnectionBinding,
    private val retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    companion object{
        fun create(parent: ViewGroup,retry: () -> Unit): PlaylistsLoadStateViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.no_internet_connection,parent,false)
            val binding = NoInternetConnectionBinding.bind(view)
            return PlaylistsLoadStateViewHolder(binding,retry)
        }
    }

}