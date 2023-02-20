package com.starbucks.peru.ui.flows.sign_off.fragments.home.repository

import com.starbucks.peru.data.EcommerceApiService
import com.starbucks.peru.data.RewardApiService
import com.starbucks.peru.di.service.NetworkModule.sharedPref
import com.starbucks.peru.di.service.TOKEN_REWARD
import com.starbucks.peru.domain.response.SBPromotionsCarouselModel
import com.starbucks.peru.domain.response.SBPromotionsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(private val rewardApi: RewardApiService) {

    suspend fun getPromotions(): List<SBPromotionsModel> = withContext(Dispatchers.IO) {
        val response = rewardApi.getPromotions("${sharedPref?.getString(TOKEN_REWARD)}")?.body()
        response!!
    }
    suspend fun getPromotionsCarousel(): List<SBPromotionsCarouselModel> = withContext(Dispatchers.IO) {
        val response = rewardApi.getPromotionsReward("${sharedPref?.getString(TOKEN_REWARD)}")?.body()
        response!!
    }

}