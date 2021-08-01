package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.data.model.CategoryModel
import com.gpaddy.baseandroid.databinding.CategoryFragmentBinding
import com.gpaddy.baseandroid.databinding.CatetgoryDetailFragmentBinding
import com.gpaddy.baseandroid.ui.adapter.CategoryNewsAdapter
import com.gpaddy.baseandroid.ui.adapter.NewsAdapter
import com.gpaddy.baseandroid.viewmodel.CategoryViewModel
import com.gpaddy.baseandroid.viewmodel.HomeViewModel

class CategoryDetailFragment(val categoryModel: CategoryModel) : BaseFragment(){
    private lateinit var viewModel: CategoryViewModel
    override fun initViewModel() {
        viewModel=getFragmentScopeViewModel(CategoryViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.catetgory_detail_fragment,
            BR.vm,
            viewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = mBinding as CatetgoryDetailFragmentBinding

        val newsAdapter= CategoryNewsAdapter(1)
        binding.rvcNews.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= newsAdapter
        }
        mDisposable.addAll(viewModel.getNewsCate(categoryModel.url))
        viewModel.newsData.observe(viewLifecycleOwner, Observer {
            newsAdapter.submitList(it.items)
        })
    }


}