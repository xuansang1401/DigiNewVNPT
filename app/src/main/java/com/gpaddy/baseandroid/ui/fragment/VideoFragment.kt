package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.databinding.VideoFragmentBinding
import com.gpaddy.baseandroid.ui.adapter.VideoAdapter
import com.gpaddy.baseandroid.viewmodel.VideoViewModel

class VideoFragment : BaseFragment() {


    private lateinit var viewModel: VideoViewModel
    override fun initViewModel() {
        viewModel=getFragmentScopeViewModel(VideoViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.video_fragment,
            BR.vm,
            viewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=mBinding as VideoFragmentBinding

        val videoAdapter= VideoAdapter()
        binding.rcvVideo.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= videoAdapter
        }
        mDisposable.addAll(viewModel.getAllVideo())
        viewModel.videoData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
           videoAdapter.submitList(it.data)
        })
    }

}