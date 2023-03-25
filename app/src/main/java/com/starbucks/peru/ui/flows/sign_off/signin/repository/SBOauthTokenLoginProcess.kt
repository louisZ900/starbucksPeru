package com.starbucks.peru.ui.flows.sign_off.signin.repository

import android.content.Context
import com.starbucks.peru.core.preferences.SBStatusStorage
import com.starbucks.peru.core.preferences.model.enum.SBStatusEnum
import com.starbucks.peru.data.RewardApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SBOauthTokenLoginProcess @Inject constructor(@ApplicationContext private val context: Context, private val rewardApi: RewardApiService) {

    private var statusStorage: SBStatusStorage = SBStatusStorage(context)

    fun getStatus(): SBStatusEnum {
        return this.statusStorage.getFlag()
    }
}