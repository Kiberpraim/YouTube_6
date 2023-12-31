package com.geeks.youtube_6.ui.details

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.geeks.youtube_6.R
import com.geeks.youtube_6.core.base.BaseActivity
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ActivityDetailsBinding
import com.geeks.youtube_6.ui.playlists.adapter.PlaylistsAdapter
import com.geeks.youtube_6.ui.player.PlayerActivity
import com.geeks.youtube_6.ui.playlists.paging_load_states.PlaylistsLoadStateAdapter
import com.geeks.youtube_6.utils.Constants
import com.geeks.youtube_6.utils.sendData
import kotlinx.coroutines.launch
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
            btnPlay.setOnClickListener {
                sendData(PlayerActivity(), Constants.DETAIL_KEY, model)
            }
        }
    }

    override fun initLiveData() {
        super.initLiveData()
        /*viewModel.getDetails(model.id).observe(this) { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {

                    adapter.setListModel(response.data?.items)

                    binding.btnPlay.setOnClickListener {
                        sendData(
                            PlayerActivity(),
                            Constants.DETAIL_KEY,
                            response.data?.items?.get(0)
                        )
                    }

                    viewModel.loading.value = false
                    binding.noInternet.root.visibility = View.GONE
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
        }*/

        lifecycleScope.launch {
            viewModel.getDetails(model.id).observe(this@DetailsActivity) { pagingData ->
                binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    footer = PlaylistsLoadStateAdapter {},
                    header = PlaylistsLoadStateAdapter {},
                )
                adapter.submitData(
                    lifecycle = lifecycle, pagingData = pagingData
                )
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
        binding.noInternet.btnTryAgain.setOnClickListener {
            checkInternetConnection()
            initLiveData()
        }
    }

    override fun checkInternetConnection() {
        super.checkInternetConnection()
        viewModel.isOnline(this).observe(this) { isOnline ->
            if (!isOnline) {
                binding.noInternet.root.visibility = View.VISIBLE
            }else{
                binding.noInternet.root.visibility = View.GONE
            }
        }
    }

    private fun onItemClick(playlistsModel: PlaylistsModel.Item) =
        sendData(PlayerActivity(), Constants.DETAIL_KEY, playlistsModel)

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}