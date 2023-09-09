package com.geeks.youtube_6.ui.details

import android.view.View
import android.widget.Toast
import com.geeks.youtube_6.R
import com.geeks.youtube_6.core.base.BaseActivity
import com.geeks.youtube_6.core.network.Resource
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ActivityDetailsBinding
import com.geeks.youtube_6.ui.playlists.PlaylistsAdapter
import com.geeks.youtube_6.ui.video.VideoActivity
import com.geeks.youtube_6.utils.Constants
import com.geeks.youtube_6.utils.sendData
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {
    override fun inflateViewBinding(): ActivityDetailsBinding =
        ActivityDetailsBinding.inflate(layoutInflater)

    override val viewModel: DetailsViewModel by viewModel()

    private val model by lazy { intent.extras?.getSerializable(Constants.PLAYLIST_KEY) as PlaylistsModel.Item }

    private val adapter = PlaylistsAdapter(get(), this::onItemClick)

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter

        with(binding) {
            tvPlaylistName.text = model.snippet.title
            tvDescription.text = model.snippet.description
            tvNumberOfVideos.text =
                model.contentDetails.itemCount.toString() + getString(R.string.video_series)
        }
    }

    override fun initLiveData() {
        super.initLiveData()
        // val playlistId: String = intent.getStringExtra(Constants.PLAYLIST_ID_KEY)!!
        viewModel.getDetails(model.id).observe(this) { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {

                    adapter.setListModel(response.data?.items)
                    viewModel.loading.value = false
                    binding.layoutNoInternet.root.visibility = View.GONE
                    Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
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
        binding.btnBack.setOnClickListener {
            finish()
        }
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

    private fun onItemClick(playlistsModel: PlaylistsModel.Item) =
        sendData(VideoActivity(), Constants.DETAIL_KEY, playlistsModel)
}
