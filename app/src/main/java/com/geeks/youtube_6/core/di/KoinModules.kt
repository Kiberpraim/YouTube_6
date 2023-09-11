package com.geeks.youtube_6.core.di

import com.geeks.youtube_6.core.network.networkModule

val koinModules = listOf(
    repositoryModule,
    networkModule,
    remoteDataSourceModule,
    viewModelModule,
)