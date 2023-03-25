package com.starbucks.peru.ui.flows.shared.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.starbucks.peru.R
import com.starbucks.peru.core.constants.*
import com.starbucks.peru.core.helpers.SBSingleLiveEvent
import com.starbucks.peru.core.preferences.SBSharedPreferences
import com.starbucks.peru.core.preferences.model.enum.SBStatusEnum
import com.starbucks.peru.core.viewmodels.SBBaseViewModel
import com.starbucks.peru.ui.flows.sign_off.home.actions.SBHomeAction
import com.starbucks.peru.ui.flows.sign_off.signin.repository.SBOauthTokenLoginProcess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SBHomeViewModel@Inject constructor(
    private val repository: SBOauthTokenLoginProcess,
    private val sharedPref: SBSharedPreferences
): SBBaseViewModel() {
    private val _action: SBSingleLiveEvent<SBHomeAction> = SBSingleLiveEvent()

    private var status = false
    private val _enableShake = MutableLiveData<Boolean>()

    fun getAction(): LiveData<SBHomeAction> = _action

    fun refreshData() {
        status = repository.getStatus() == SBStatusEnum.ACTIVE
        _enableShake.value = status
    }

    fun getSection(bottomAction: Int): Boolean {
        val section = getSectionModel(bottomAction)

        _action.value = SBHomeAction.ShowSection(
            section
        )

        return section != ""
    }

    private fun getSectionModel(bottomAction: Int): String {
        return when (bottomAction) {
            R.id.navigation_home_sign_off -> {
                return when (status) {
                    true -> SB_TAG_FRAGMENT_HOME_CLIENT
                    false -> SB_TAG_FRAGMENT_HOME_CUSTOMER
                }
            }
            R.id.navigation_cards_sing_off -> {
                return when (status) {
                    true -> SB_TAG_FRAGMENT_CARDS_CLIENT
                    false -> SB_TAG_FRAGMENT_CARDS_CUSTOMER
                }
            }
            R.id.navigation_orders -> {
                //return SB_TAG_FRAGMENT_STORES_SHARED
                return SB_TAG_FRAGMENT_ORDER_SHARED
            }
            R.id.navigation_setting -> {
                return when (status) {
                    true -> SB_TAG_FRAGMENT_SETTINGS_CLIENT
                    false -> SB_TAG_FRAGMENT_SETTINGS_CUSTOMER
                }
            }
            else -> ""
        }
    }
}