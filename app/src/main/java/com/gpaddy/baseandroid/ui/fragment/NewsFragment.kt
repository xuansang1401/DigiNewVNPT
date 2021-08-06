package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
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
import com.gpaddy.baseandroid.data.model.api.Item
import com.gpaddy.baseandroid.databinding.HomeFragmentBinding
import com.gpaddy.baseandroid.databinding.HomeFragmentBindingImpl
import com.gpaddy.baseandroid.databinding.NewsFragmentBinding
import com.gpaddy.baseandroid.extensions.showToast
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
        viewModel.addHis(item)
        binding.tvTitle.text = item.title
        binding.webView.loadData(item.description, "text/html", "utf-8")
        val adapte = CategoryNewsAdapter(9)
        binding.rcvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapte
        }
        mDisposable.addAll(viewModel.getNewsHot())
        viewModel.newsData.observe(viewLifecycleOwner, Observer {
            adapte.submitList(it.items)
        })
        binding.btnBack.setOnClickListener {
            nav().navigateUp()
        }
        binding.btnMore.setOnClickListener {
            showMenu(
                it, item
            )
        }
    }

    fun showMenu(v: View,data: Item) {
        PopupMenu(v.context, v).apply {
            // MainActivity implements OnMenuItemClickListener
            inflate(R.menu.news_menu)
            show()
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.click_bookmark -> {
                        viewModel.addBookmark(data )
                        showToast("Thêm thành công")
                        true
                    }
                    R.id.click_fav -> {
                        viewModel.addFav(data)
                        showToast("Thêm thành công")

                        true
                    }
                    R.id.click_save -> {
                        viewModel.addDownload(data)
                        showToast("Thêm thành công")

                        true
                    }
                    else -> false
                }
            }

        }
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