package com.gpaddy.baseandroid.base.ui

import android.content.Intent
import android.net.Uri
import android.view.animation.Animation
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


abstract class BaseFragment : DataBindingFragment(){
    private lateinit var mFragmentProvider: ViewModelProvider
    protected var mAnimationLoaded = false

    protected open fun <T : ViewModel?> getFragmentScopeViewModel(modelClass: Class<T>): T {
        mFragmentProvider = ViewModelProvider(this)
        return mFragmentProvider[modelClass]
    }

    protected open fun <T : ViewModel?> getActivityScopeViewModel(modelClass: Class<T>): T {
        mFragmentProvider = ViewModelProvider(mActivity)
        return mFragmentProvider[modelClass]
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        HANDLER.postDelayed(Runnable {
            if (!mAnimationLoaded) {
                mAnimationLoaded = true
                loadInitData()
            }
        }, 280)
        return super.onCreateAnimation(transit, enter, nextAnim)
    }


    protected open fun loadInitData() {
    }

    protected open fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }
    protected open fun openUrlInBrowser(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
    protected open fun showLongToast(text: String?) {
        Toast.makeText(mActivity.applicationContext, text, Toast.LENGTH_LONG).show()
    }

    protected open fun showShortToast(text: String?) {
        Toast.makeText(mActivity.applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    protected open fun showLongToast(stringRes: Int) {
        showLongToast(mActivity.applicationContext.getString(stringRes))
    }

    protected open fun showShortToast(stringRes: Int) {
        showShortToast(mActivity.applicationContext.getString(stringRes))
    }

}