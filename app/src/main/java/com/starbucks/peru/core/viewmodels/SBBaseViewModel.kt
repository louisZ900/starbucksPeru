package com.starbucks.peru.core.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.starbucks.peru.core.helpers.SBSingleLiveEvent

abstract class SBBaseViewModel : ViewModel() {
    protected val showProgress = SBSingleLiveEvent<Boolean>()
    protected val showErrorMessage = SBSingleLiveEvent<String>()
    val TAG = javaClass.simpleName + ": "

    fun getShowProgress(): LiveData<Boolean> = showProgress
    fun getShowErrorMessage(): LiveData<String> = showErrorMessage

}