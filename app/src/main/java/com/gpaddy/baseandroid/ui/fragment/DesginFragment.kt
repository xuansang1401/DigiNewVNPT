package com.gpaddy.baseandroid.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.google.android.material.internal.NavigationMenuItemView
import com.orhanobut.logger.Logger
import com.pixplicity.easyprefs.library.Prefs
import com.gpaddy.baseandroid.BR
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.data.model.api.Demo
import com.gpaddy.baseandroid.base.ui.BaseFragment
import com.gpaddy.baseandroid.base.ui.DataBindingConfig
import com.gpaddy.baseandroid.databinding.DesginFragmentBinding
import com.gpaddy.baseandroid.util.NavigationIconClickListener
import com.gpaddy.baseandroid.viewmodel.HomeViewModel


class DesginFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    override fun initViewModel() {
        viewModel=getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.desgin_fragment,
            BR.vm,
            viewModel
        )
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view= mBinding as DesginFragmentBinding
        (activity as AppCompatActivity).setSupportActionBar(view.appBar)

        view.appBar.setNavigationOnClickListener(
            NavigationIconClickListener(
            activity!!,
            view.productGrid,
            AccelerateDecelerateInterpolator(),
            ContextCompat.getDrawable(context!!, R.drawable.ic_add_on_secondary_24dp), // Menu open icon
            ContextCompat.getDrawable(context!!, R.drawable.ic_bookmark_24dp)
        )
        ) // Menu close icon

        // Set up the RecyclerView
    }

    inner class ClickProxy {
    }
}