package com.gpaddy.baseandroid.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.databinding.ActivityCanhanBinding
import com.gpaddy.baseandroid.theu.ttvnpt.caidat
import com.gpaddy.baseandroid.ui.adapter.CategoryNewsAdapter
import com.gpaddy.baseandroid.util.AppContain
import com.gpaddy.baseandroid.viewmodel.HomeViewModel
import com.pixplicity.easyprefs.library.Prefs


class AccountFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel

    companion object {
        const val ID = 2
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


        val name = Prefs.getString(AppContain.USER_NAME, "Nguyen Theu")
        val avatar = Prefs.getString(AppContain.USER_AVATAR, "")

        binding.tvName.text = name
        Glide.with(this).load(avatar).error(R.drawable.baseline_account_circle_black_36dp)
            .into(binding.avatar)

        binding.btnSetting.setOnClickListener {
            val intent = Intent(context, caidat::class.java)
            startActivity(intent)
        }
        binding.rcvBookmak.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryNewsAdapter(ID)
        }
        binding.rcvDownload.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryNewsAdapter(ID)
        }
        binding.rcvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryNewsAdapter(ID)
        }
        binding.rcvLike.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryNewsAdapter(ID)
        }

        viewModel.getBook().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.llBook.visibility = View.GONE
            else {
                binding.llBook.visibility = View.VISIBLE
                (binding.rcvBookmak.adapter as CategoryNewsAdapter).submitList(it)
            }


        })
        viewModel.getFav().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.llFav.visibility = View.GONE
            else {
                binding.llFav.visibility = View.VISIBLE
                (binding.rcvLike.adapter as CategoryNewsAdapter).submitList(it)
            }


        })
        viewModel.getDown().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.llDow.visibility = View.GONE
            else {
                binding.llDow.visibility = View.VISIBLE
                (binding.rcvDownload.adapter as CategoryNewsAdapter).submitList(it)
            }


        })
        viewModel.getHistory().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) binding.llHis.visibility = View.GONE
            else {
                binding.llHis.visibility = View.VISIBLE
                (binding.rcvHistory.adapter as CategoryNewsAdapter).submitList(it)

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

    fun openFragmentDetail(status: Int) {
        val directions = AccountFragmentDirections.actionAccountFragmentToListNewsFragment(status)
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