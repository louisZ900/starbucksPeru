package com.starbucks.peru.data

import com.starbucks.peru.domain.response.SBOauthTokenNoLoginModel
import com.starbucks.peru.domain.response.SBPromotionsCarouselModel
import com.starbucks.peru.domain.response.SBPromotionsModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RewardApiService {
    companion object {
        const val ENDPOINT = "https://testapi.starbucksrewards.com.pe/"
    }

    @POST("v1/oauth/token?market=PE&platform=android")
    suspend fun tokenRefresh(@Query("sig") sig: String, @Body body: RequestBody): Response<SBOauthTokenNoLoginModel>

    @POST("v1/oauth/token?market=PE&platform=android")
    fun tokenRefreshV2(@Query("sig") sig: String, @Body body: RequestBody): Call<SBOauthTokenNoLoginModel>//Call<SBOauthTokenNoLoginModel>

    @GET("v1/cms/promociones?language=en")
    suspend fun getPromotions(@Query("access_token") accessToken: String): Response<List<SBPromotionsModel>>

    @GET("v1/cms/cta?language=en")
    suspend fun getPromotionsReward(@Query("access_token") accessToken: String): Response<List<SBPromotionsCarouselModel>>
}