package com.geeks.youtube_6.core.di

import com.geeks.youtube_6.data.repository.Repository
import org.koin.dsl.module


val repositoryModule = module {
    single { Repository(get()) }
}