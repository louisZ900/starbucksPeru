package com.starbucks.peru.ui.repository

import com.starbucks.peru.data.RewardApiService
import com.starbucks.peru.domain.response.SBOauthTokenNoLoginModel
import kotlinx.coroutines.Dispatchers
import okhttp3.RequestBody
import retrofit2.http.Body
import javax.inject.Inject

class TokenRepository @Inject constructor(private val rewardApi: RewardApiService) {

    suspend fun tokenRefresh(sig: String, body: RequestBody): SBOauthTokenNoLoginModel = with(Dispatchers.IO){
        val response = rewardApi.tokenRefresh(sig, body)?.body()
        response!!
    }


}