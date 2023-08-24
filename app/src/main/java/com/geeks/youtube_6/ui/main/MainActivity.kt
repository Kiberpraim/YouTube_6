package com.geeks.youtube_6.ui.main

import androidx.lifecycle.ViewModelProvider
import com.geeks.youtube_6.core.base.BaseActivity
import com.geeks.youtube_6.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun inflateViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun initViewModel(): MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

    private val adapter = PlaylistsAdapter()

    override fun initLiveData() {
        super.initLiveData()
        viewModel.playlists.observe(this){
            adapter.setListModel(it)
        }
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        viewModel.getPlayList()
    }
}