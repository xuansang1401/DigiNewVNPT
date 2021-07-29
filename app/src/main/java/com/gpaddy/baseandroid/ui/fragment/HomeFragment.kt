package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.orhanobut.logger.Logger
import com.pixplicity.easyprefs.library.Prefs
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.data.model.api.Demo
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
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

    }

    inner class ClickProxy {
        fun addData() {
            Logger.d("${viewModel.ten.get()}, ho: ${viewModel.ho.get()}");

//            Log.e("sang", "${viewModel.ten.get()}, ho: ${viewModel.ho.get()}")
            val id=viewModel.id.get()
            val name=viewModel.ho.get()
            val ho= viewModel.ten.get()
            if (id.isNullOrEmpty() || name.isNullOrEmpty() || ho.isNullOrEmpty()){
                showLongToast("Khong null")
            }else{
                viewModel.data.value= Demo(id,name ,ho)
                Prefs.putString("SANG", name)
            }

        }

        fun openFragment(){
//            val dir = HomeFragmentDirections.actionHomeFragmentToDetailFragment("sang")
//            nav().navigate(dir)
            nav().navigate(R.id.detailFragment, bundleOf("title" to "Do Xuan Sang"))
        }
    }
}