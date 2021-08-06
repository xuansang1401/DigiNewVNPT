package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.orhanobut.logger.Logger
import com.pixplicity.easyprefs.library.Prefs
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.data.model.api.Item
import com.gpaddy.baseandroid.databinding.ActivityCanhanBinding
import com.gpaddy.baseandroid.databinding.HomeFragmentBinding
import com.gpaddy.baseandroid.databinding.HomeFragmentBindingImpl
import com.gpaddy.baseandroid.databinding.NewsFragmentBinding
import com.gpaddy.baseandroid.extensions.showToast
import com.gpaddy.baseandroid.ui.adapter.CategoryNewsAdapter
import com.gpaddy.baseandroid.ui.adapter.NewsAdapter
import com.gpaddy.baseandroid.viewmodel.HomeViewModel


class AccountFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel
    companion object{
        const val ID=2
    }
    override fun initViewModel() {
        viewModel = getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.activity_canhan,
            BR.vm,
            viewModel
        )
//            .addBindingParam(BR, ClickProxy())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mBinding as ActivityCanhanBinding

        binding.rcvBookmak.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= CategoryNewsAdapter(ID)
        }
        binding.rcvDownload.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= CategoryNewsAdapter(ID)
        }
        binding.rcvHistory.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= CategoryNewsAdapter(ID)
        }
        binding.rcvLike.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= CategoryNewsAdapter(ID)
        }

        viewModel.getBook().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.llBook.visibility=View.GONE
            else {
                binding.llBook.visibility=View.VISIBLE
                (binding.rcvBookmak.adapter as  CategoryNewsAdapter).submitList(it)
            }


        })
        viewModel.getFav().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.llFav.visibility=View.GONE
            else {
                binding.llFav.visibility=View.VISIBLE
                (binding.rcvLike.adapter as  CategoryNewsAdapter).submitList(it)
            }


        })
        viewModel.getDown().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.llDow.visibility=View.GONE
            else {
                binding.llDow.visibility=View.VISIBLE
                (binding.rcvDownload.adapter as  CategoryNewsAdapter).submitList(it)
            }


        })
        viewModel.getHistory().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.llHis.visibility=View.GONE
            else {
                binding.llHis.visibility=View.VISIBLE
                (binding.rcvHistory.adapter as  CategoryNewsAdapter).submitList(it)

            }

        })

        binding.btnBack.setOnClickListener {
            nav().navigateUp()
        }

        binding.btnBook.setOnClickListener {
            openFragmentDetail(4)
        }
        binding.btnDow.setOnClickListener {
            openFragmentDetail(3)
        }
        binding.btnFav.setOnClickListener {
            openFragmentDetail(2)
        }
        binding.btnHis.setOnClickListener {
            openFragmentDetail(1)
        }

    }

    fun openFragmentDetail(status: Int){
        val directions= AccountFragmentDirections.actionAccountFragmentToListNewsFragment(status)
        nav().navigate(directions)
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