package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.data.model.db.HistoryModel
import com.gpaddy.baseandroid.databinding.LiveRoomFragmentBinding
import com.gpaddy.baseandroid.ui.adapter.HistoryAdapter
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.viewmodel.LiveRoomViewModel
import java.util.*

class LiveRoomFragment : BaseFragment() {


    private lateinit var viewModel: LiveRoomViewModel
    override fun initViewModel() {
        viewModel=getFragmentScopeViewModel(LiveRoomViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.live_room_fragment,
            BR.vm,
            viewModel
        )
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=mBinding as LiveRoomFragmentBinding
        binding.rcvHistory.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= HistoryAdapter(viewModel.getDaoDatabase())
        }
        viewModel.getAll().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            (binding.rcvHistory.adapter as HistoryAdapter).submitList(it)
        })
    }
    inner class ClickProxy {
        fun addHis() {
            val id = viewModel.id.get()
            val name = viewModel.title.get()
            if (id.isNullOrEmpty() || name.isNullOrEmpty()) {
                showLongToast("Not null")
                return
            }
            viewModel.addData(HistoryModel(id, name, Date().time))
        }
    }
}