package com.gpaddy.baseandroid.base.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable


@AndroidEntryPoint
abstract class DataBindingFragment: Fragment(){
    protected lateinit var  mBinding: ViewDataBinding
    protected lateinit var mActivity: AppCompatActivity
     val HANDLER = Handler()
    protected val mDisposable= CompositeDisposable()
    protected abstract fun initViewModel()
    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBindingConfig = getDataBindingConfig()

        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, dataBindingConfig.layout, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)
        val bindingParams = dataBindingConfig.bindingParams
        if (!bindingParams.isEmpty()){
            for (i in 0 until bindingParams.size()){
                binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
            }
        }
        mBinding = binding
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.unbind()
        mDisposable.clear()
    }
}