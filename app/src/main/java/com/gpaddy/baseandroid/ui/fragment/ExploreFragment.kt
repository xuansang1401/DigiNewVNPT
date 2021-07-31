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
        binding.btnClip.setOnClickListener{

        }
        binding.btnHealth.setOnClickListener{

        }
        binding.btnMovie.setOnClickListener{

        }
        binding.btnMusic.setOnClickListener{

        }
        binding.btnMytv.setOnClickListener{

        }

    }


}