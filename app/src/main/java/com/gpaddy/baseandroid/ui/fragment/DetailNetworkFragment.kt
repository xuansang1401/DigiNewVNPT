package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.orhanobut.logger.Logger
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.viewmodel.NetworkViewModel

class DetailNetworkFragment : BaseFragment() {
    private lateinit var mViewModel: NetworkViewModel

    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(NetworkViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.detai_fragment,
            BR.vm,
            mViewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDisposable.addAll(mViewModel.getRXBus())
        mViewModel.data.observe(viewLifecycleOwner, Observer {
            Logger.d("sang: $it")
        })
    }
}