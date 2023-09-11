package com.geeks.youtube_6.core.di

import com.geeks.youtube_6.core.network.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(get()) }
}