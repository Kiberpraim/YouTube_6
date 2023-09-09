package com.geeks.youtube_6.ui.video

import com.geeks.youtube_6.core.base.BaseActivity
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ActivityVideoBinding
import com.geeks.youtube_6.ui.playlists.PlaylistsViewModel
import com.geeks.youtube_6.utils.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseActivity<ActivityVideoBinding, PlaylistsViewModel>() {
    override val viewModel: PlaylistsViewModel by viewModel()
    private val model by lazy { intent.extras?.getSerializable(Constants.DETAIL_KEY) as PlaylistsModel.Item }

    override fun inflateViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        with(binding) {
            videoView.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(model.contentDetails.videoId, 0f)
                        // youTubePlayer.loadVideo(modelVideo.contentDetails.videoId, 0f)
                    }
                }
            )
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}