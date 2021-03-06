package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.ui.adapter.NewsAdapter
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.databinding.ExploreFragmentBinding
import com.gpaddy.baseandroid.viewmodel.HomeViewModel
import com.gpaddy.baseandroid.viewmodel.NetworkViewModel

class ExploreFragment : BaseFragment(){
    private lateinit var mViewModel: NetworkViewModel
    private lateinit var mState: HomeViewModel

    override fun initViewModel() {
        mViewModel=getFragmentScopeViewModel(NetworkViewModel::class.java)
        mState=getActivityScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.explore_fragment,
            BR.vm,
            mViewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mBinding as ExploreFragmentBinding
//        val divider = MaterialDividerItemDecoration(context!!, LinearLayoutManager.VERTICAL )
        binding.btnMovie.setOnClickListener{
            openFragmentDetail(0)
        }
        binding.btnClip.setOnClickListener{
            openFragmentDetail(1)

        }
        binding.btnMusic.setOnClickListener{
            openFragmentDetail(2)

        }
        binding.btnHealth.setOnClickListener{
            openFragmentDetail(3)

        }
        binding.btnMytv.setOnClickListener{
            openFragmentDetail(4)

        }
    }

    fun openFragmentDetail(id: Int){
        val dir= ExploreFragmentDirections.actionExploreFragmentToExploreDetailFragment(id)
        nav().navigate(dir)
    }


}