package com.gpaddy.baseandroid.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.databinding.HomeFragmentBinding
import com.gpaddy.baseandroid.theu.ttvnpt.timkiem
import com.gpaddy.baseandroid.ui.activity.MainActivity
import com.gpaddy.baseandroid.ui.adapter.NewsAdapter
import com.gpaddy.baseandroid.viewmodel.HomeViewModel


class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    override fun initViewModel() {
        viewModel=getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.home_fragment,
            BR.vm,
            viewModel
        )
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mBinding as HomeFragmentBinding
        val adapte= NewsAdapter()
        binding.rcvNews.apply {
            layoutManager=LinearLayoutManager(context)
            adapter= adapte
        }
        mDisposable.addAll(viewModel.getNewsHome())
        viewModel.newsData.observe(viewLifecycleOwner, Observer {
            adapte.submitList(it.items)
        })
        binding.button
    }

    inner class ClickProxy {
        fun openAccount() {
            val dir= HomeFragmentDirections.actionHomeFragmentToAccountFragment()
            nav().navigate(dir)
        }

        fun openSearch(){
            val intent = Intent( context, timkiem::class.java)
            startActivity(intent)
        }
    }
}