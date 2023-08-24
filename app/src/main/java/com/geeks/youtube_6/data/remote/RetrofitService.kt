package com.geeks.youtube_6.data.remote

import com.geeks.youtube_6.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    var retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    var api = retrofit.create(YouTubeApi::class.java)
}