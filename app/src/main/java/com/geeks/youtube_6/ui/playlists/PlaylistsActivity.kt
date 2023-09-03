package com.geeks.youtube_6.ui.playlists

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.geeks.youtube_6.R
import com.geeks.youtube_6.core.base.BaseActivity
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.databinding.ActivityPlaylistsBinding
import com.geeks.youtube_6.ui.details.DetailsActivity
import com.geeks.youtube_6.utils.Constants
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    override fun inflateViewBinding(): ActivityPlaylistsBinding =
        ActivityPlaylistsBinding.inflate(layoutInflater)

    override val viewModel: PlaylistsViewModel by viewModel()

    private val adapter = PlaylistsAdapter(get(),this::onItemClick)

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
    }

    override fun initLiveData() {
        super.initLiveData()
        viewModel.getPlayList().observe(this) { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    adapter.setListModel(response.data?.items)
                    viewModel.loading.value = false
                    binding.layoutNoInternet.root.visibility = View.GONE
                }

                Resource.Status.ERROR -> {
                    Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
                    viewModel.loading.value = false
                }

                Resource.Status.LOADING -> {
                    Toast.makeText(this, getString(R.string.loading), Toast.LENGTH_SHORT).show()
                    viewModel.loading.value = true
                }
            }
        }

        viewModel.loading.observe(this) { status ->
            if (status) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }
    }

    override fun initListener() {
        super.initListener()
        binding.layoutNoInternet.btnTryAgain.setOnClickListener {
            initLiveData()
        }
    }

    override fun checkInternetConnection() {
        super.checkInternetConnection()
        viewModel.isOnline(this).observe(this) { isOnline ->
            if (!isOnline) {
                binding.layoutNoInternet.root.visibility = View.VISIBLE
            }
        }
    }

    private fun onItemClick(
        playlistId: String,
        title: String,
        description: String,
        numberOfVideos: String
    ) {
        val intent = Intent(this, DetailsActivity::class.java)

        intent.putExtra(Constants.PLAYLIST_ID_KEY, playlistId)
        intent.putExtra(Constants.TITLE_KEY, title)
        intent.putExtra(Constants.DESCRIPTION_KEY, description)
        intent.putExtra(Constants.NUMBER_OF_VIDEOS_KEY, numberOfVideos)

        startActivity(intent)
    }
}