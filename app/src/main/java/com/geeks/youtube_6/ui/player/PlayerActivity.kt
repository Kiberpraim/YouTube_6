package com.geeks.youtube_6.ui.player

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.geeks.youtube_6.R
import com.geeks.youtube_6.core.base.BaseActivity
import com.geeks.youtube_6.data.model.PlaylistsModel
import com.geeks.youtube_6.databinding.ActivityPlayerBinding
import com.geeks.youtube_6.utils.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>() {
    override val viewModel: PlayerViewModel by viewModel()
    private val model by lazy { intent.extras?.getSerializable(Constants.DETAIL_KEY) as PlaylistsModel.Item }

    override fun inflateViewBinding(): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun initView() {

        super.initView()
        with(binding) {
            videoView.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(model.contentDetails.videoId ?: "BzVlosMz2mk", 0f)
                    }
                }
            )

            btnBack.setOnClickListener {
                finish()
            }

            btnDownload.setOnClickListener {
                val builder = AlertDialog.Builder(this@PlayerActivity)
                val dialogView = layoutInflater.inflate(R.layout.alert_dialog, null)
                val btnDownload = dialogView.findViewById<Button>(R.id.btnDownload)

                builder.setView(dialogView)

                val alertDialog = builder.create()
                btnDownload.setOnClickListener {
                    alertDialog.cancel()
                }

                alertDialog.show()
            }
            tvTitle.text = model.snippet.title
            tvDescription.text = model.snippet.description
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
            initView()
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
}