package com.geeks.youtube_6.core.base

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<
        VB : ViewBinding,
        VM : ViewModel
        > : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    protected abstract fun inflateViewBinding(): VB
    protected abstract fun initViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        viewModel = initViewModel()

        checkInternetConnection()
        initView()
        initLiveData()
        initListener()
    }

    open fun initListener() {}

    open fun initLiveData() {}

    open fun initView() {}

    open fun checkInternetConnection() {}
}