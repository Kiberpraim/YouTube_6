package com.geeks.youtube_6.core.network

import com.geeks.youtube_6.BuildConfig
import com.geeks.youtube_6.data.remote.DetailsApiService
import com.geeks.youtube_6.data.remote.PlaylistsApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { provideInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideRetrofit(get()) }
    single { providePlaylistsApiService(get()) }
    single { provideDetailsApiService(get()) }
}

private fun provideInterceptor(): Interceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
    OkHttpClient().newBuilder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

private fun providePlaylistsApiService(retrofit: Retrofit): PlaylistsApiService =
    retrofit.create(PlaylistsApiService::class.java)

private fun provideDetailsApiService(retrofit: Retrofit): DetailsApiService =
    retrofit.create(DetailsApiService::class.java)