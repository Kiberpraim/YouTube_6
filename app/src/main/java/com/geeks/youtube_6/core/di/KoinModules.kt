package com.geeks.youtube_6.core.di

import com.geeks.youtube_6.core.network.RemoteDataSource
import com.geeks.youtube_6.core.network.networkModule
import com.geeks.youtube_6.data.repository.Repository
import org.koin.dsl.module

val koinModules = listOf(
    repositoryModule,
    networkModule,
    remoteDataSourceModule,
    viewModelModule,
)