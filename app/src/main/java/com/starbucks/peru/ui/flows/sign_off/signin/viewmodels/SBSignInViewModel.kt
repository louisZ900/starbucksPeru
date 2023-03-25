package com.starbucks.peru.ui.flows.sign_off.signin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.starbucks.peru.core.preferences.SBSharedPreferences
import com.starbucks.peru.core.preferences.model.enum.SBStatusEnum
import com.starbucks.peru.core.viewmodels.SBBaseViewModel
import com.starbucks.peru.ui.flows.sign_off.signin.repository.SBOauthTokenLoginProcess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SBSignInViewModel@Inject constructor(
    private val repository: SBOauthTokenLoginProcess,
    private val sharedPref: SBSharedPreferences
): SBBaseViewModel() {

    private var status = false
    private val _enableShake = MutableLiveData<Boolean>()
    val enableShake: LiveData<Boolean> = _enableShake

    fun refreshData() {
        status = repository.getStatus() == SBStatusEnum.ACTIVE
        _enableShake.value = status
    }
}