package com.geeks.youtube_6.core.di

import com.geeks.youtube_6.ui.details.DetailsViewModel
import com.geeks.youtube_6.ui.player.PlayerViewModel
import com.geeks.youtube_6.ui.playlists.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { PlayerViewModel() }
}