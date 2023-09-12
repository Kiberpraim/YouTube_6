package com.geeks.youtube_6.ui.playlists

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.geeks.youtube_6.core.base.BaseActivity
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ActivityPlaylistsBinding
import com.geeks.youtube_6.ui.details.DetailsActivity
import com.geeks.youtube_6.ui.playlists.adapter.PlaylistsAdapter
import com.geeks.youtube_6.ui.playlists.paging_load_states.PlaylistsLoadStateAdapter
import com.geeks.youtube_6.utils.Constants
import com.geeks.youtube_6.utils.sendData
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    override fun inflateViewBinding(): ActivityPlaylistsBinding =
        ActivityPlaylistsBinding.inflate(layoutInflater)

    override val viewModel: PlaylistsViewModel by viewModel()

    private val adapter = PlaylistsAdapter(get(), this::onItemClick)

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
    }

    override fun initLiveData() {
        super.initLiveData()

        /*        viewModel.getPlayList().observe(this) { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    adapter.setListModel(response.data?.items)
                    viewModel.loading.value = false
                    binding.noInternet.root.visibility = View.GONE
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
            viewModel.getPlayList().observe(this@PlaylistsActivity) { pagingData ->
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
        sendData(DetailsActivity(), Constants.PLAYLIST_KEY, playlistsModel)

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}