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
import com.gpaddy.baseandroid.databinding.ListViewFragmentBinding
import com.gpaddy.baseandroid.ui.adapter.CategoryNewsAdapter
import com.gpaddy.baseandroid.ui.adapter.NewsAdapter
import com.gpaddy.baseandroid.viewmodel.CategoryViewModel
import com.gpaddy.baseandroid.viewmodel.HomeViewModel

class ListNewsFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel
    private val args: ListNewsFragmentArgs by navArgs()
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.list_view_fragment,
            BR.vm,
            viewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mBinding as ListViewFragmentBinding
        val status = args.id
        binding.tvTitle.text = getTitle(status)
        val newsAdapter = CategoryNewsAdapter(3)
        binding.rvcNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
        viewModel.getListAll(status).observe(viewLifecycleOwner, Observer {
            newsAdapter.submitList(it)
        })

        binding.btnDe.setOnClickListener {
            viewModel.delete(status)
            nav().navigateUp()
        }
    }

    fun getTitle(status: Int): String {
        return when (status) {
            1 -> "Tin đã xem"
            2 -> "Tin yêu thích"
            3 -> "Tin đã tải"
            else -> "Tin đã lưu"
        }

    }

}