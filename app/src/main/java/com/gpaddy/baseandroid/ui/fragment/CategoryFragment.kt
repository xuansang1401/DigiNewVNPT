package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View

import com.google.android.material.tabs.TabLayoutMediator
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.data.model.CategoryModel
import com.gpaddy.baseandroid.databinding.CategoryFragmentBinding
import com.gpaddy.baseandroid.ui.adapter.CategoryPagerAdapter
import com.gpaddy.baseandroid.viewmodel.CategoryViewModel


class CategoryFragment : BaseFragment() {

    private lateinit var mViewModel: CategoryViewModel
    override fun initViewModel() {
        mViewModel=getFragmentScopeViewModel(CategoryViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.category_fragment,
            BR.vm,
            mViewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= mBinding as CategoryFragmentBinding
        val listCategory= mViewModel.getListCategory()
        binding.viewPager.adapter =
            CategoryPagerAdapter(
                this,
                listCategory
                )
        TabLayoutMediator(binding.tabs, binding.viewPager, true, false)
        { tab, position ->
            tab.text = getTabTitle(position, listCategory)
        }.attach()
        // Set up the RecyclerView
    }
    private fun getTabTitle(position: Int, listCategory: MutableList<CategoryModel>): String{

        return when (position) {
            position -> listCategory[position].name
            else -> getString(R.string.home)
        }
    }
}