package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.viewmodel.HomeViewModel

class DetailFragment : BaseFragment(){
    private lateinit var viewModel: HomeViewModel
    private val args: DetailFragmentArgs by navArgs()
    override fun initViewModel() {
        viewModel=getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.detail_fragment,
            BR.vm,
            viewModel
        )
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.args, args)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    inner class ClickProxy {
        fun back() {
            nav().navigateUp()
        }

    }

}