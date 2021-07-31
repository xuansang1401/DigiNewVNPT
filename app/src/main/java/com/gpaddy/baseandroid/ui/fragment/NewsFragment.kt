package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.pixplicity.easyprefs.library.Prefs
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.databinding.HomeFragmentBinding
import com.gpaddy.baseandroid.databinding.HomeFragmentBindingImpl
import com.gpaddy.baseandroid.databinding.NewsFragmentBinding
import com.gpaddy.baseandroid.ui.adapter.CategoryNewsAdapter
import com.gpaddy.baseandroid.ui.adapter.NewsAdapter
import com.gpaddy.baseandroid.viewmodel.HomeViewModel


class NewsFragment : BaseFragment() {

    private val newsArgs: NewsFragmentArgs by navArgs()
    private lateinit var viewModel: HomeViewModel
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.news_fragment,
            BR.vm,
            viewModel
        )
//            .addBindingParam(BR, ClickProxy())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mBinding as NewsFragmentBinding
        val item = newsArgs.item ?: return
        binding.tvTitle.text = item.title
        binding.webView.loadData(item.description, "text/html", "utf-8")
        val adapte = CategoryNewsAdapter()
        binding.rcvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapte
        }
        mDisposable.addAll(viewModel.getNewsHot())
        viewModel.newsData.observe(viewLifecycleOwner, Observer {
            adapte.submitList(it.items)
        })
    }

//    inner class ClickProxy {
//        fun addData() {
//
//
//        }
//
//        fun openFragment(){
////
//        }
//    }
}