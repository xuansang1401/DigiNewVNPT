package com.gpaddy.baseandroid.base.ui

import android.util.SparseArray
import androidx.lifecycle.ViewModel

class DataBindingConfig constructor(
    val layout: Int,
    val vmVariableId: Int,
    val stateViewModel: ViewModel
) {
    val bindingParams = SparseArray<Any>()

    fun addBindingParam(
        variableId: Int,
       ob: Any
    ): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, ob)
        }
        return this
    }

}