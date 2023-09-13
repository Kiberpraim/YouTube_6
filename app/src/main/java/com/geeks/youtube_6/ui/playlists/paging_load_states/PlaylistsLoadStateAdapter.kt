package com.geeks.youtube_6.ui.playlists.paging_load_states

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeks.youtube_6.R
import com.geeks.youtube_6.databinding.ActivityPlaylistsBinding

class PlaylistsLoadStateAdapter(private val retry: () -> Unit):
    LoadStateAdapter<PlaylistsLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PlaylistsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PlaylistsLoadStateViewHolder {
        return PlaylistsLoadStateViewHolder.create(parent, retry)
    }
}

class PlaylistsLoadStateViewHolder(
    private val binding: ActivityPlaylistsBinding,
    private val retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState){

        when(loadState){
            is LoadState.Error -> binding.noInternet.root.visibility = View.VISIBLE

            LoadState.Loading -> binding.progressBar.visibility = View.VISIBLE

            is LoadState.NotLoading -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
    companion object{
        fun create(parent: ViewGroup,retry: () -> Unit): PlaylistsLoadStateViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_playlists,parent,false)
            val binding = ActivityPlaylistsBinding.bind(view)
            return PlaylistsLoadStateViewHolder(binding,retry)
        }
    }

}