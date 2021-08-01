package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.databinding.VideoDetailFragmentBinding
import com.gpaddy.baseandroid.ui.adapter.VideoAdapter
import com.gpaddy.baseandroid.ui.adapter.bindImageFromUrl
import com.gpaddy.baseandroid.viewmodel.VideoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class VideoDetailFragment : BaseFragment() {


    private val args:VideoDetailFragmentArgs by navArgs()
    private lateinit var viewModel: VideoViewModel
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(VideoViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.video_detail_fragment,
            BR.vm,
            viewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mBinding as VideoDetailFragmentBinding
        val youTubePlayerView = binding.youtubePlayerView

        val item = args.item ?: return
        binding.tvTitle.text=item.title
        val videoAdapter = VideoAdapter(9)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = item.video_id
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
        binding.rcvVideo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }
        mDisposable.addAll(viewModel.getAllVideo())
        viewModel.videoData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            videoAdapter.submitList(it.data)
        })
        binding.btnBack.setOnClickListener {
            nav().navigateUp()
        }
    }

}