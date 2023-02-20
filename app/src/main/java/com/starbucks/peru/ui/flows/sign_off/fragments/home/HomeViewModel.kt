package com.starbucks.peru.ui.flows.sign_off.fragments.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.starbucks.peru.core.preferences.SBSharedPreferences
import com.starbucks.peru.core.utils.GHServiceConfig
import com.starbucks.peru.core.utils.LogUtils
import com.starbucks.peru.core.utils.Resource
import com.starbucks.peru.core.utils.SBBaseProcess
import com.starbucks.peru.core.viewmodels.SBBaseViewModel
import com.starbucks.peru.di.service.*
import com.starbucks.peru.domain.response.SBOauthTokenNoLoginModel
import com.starbucks.peru.domain.response.SBPromotionsCarouselModel
import com.starbucks.peru.domain.response.SBPromotionsModel
import com.starbucks.peru.ui.flows.sign_off.fragments.home.repository.HomeRepository
import com.starbucks.peru.ui.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.IOException
import java.security.MessageDigest
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val tokenRepository: TokenRepository,
    private val sharedPref: SBSharedPreferences,
    @ApplicationContext private val contexts: Context
) : SBBaseViewModel() {

    val promotionsLiveData: MutableLiveData<Resource<List<SBPromotionsModel>>> = MutableLiveData()
    val promotionsCarouselLiveData: MutableLiveData<Resource<List<SBPromotionsCarouselModel>>> = MutableLiveData()
    val oauthTokenLiveData: MutableLiveData<Resource<SBOauthTokenNoLoginModel>> = MutableLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var nameApi = ""

    fun getPromotions() {
        nameApi =  PROMOTIONS
        validarTime()
    }

    fun getPromotionsCorusel() {
        nameApi =  PROMOTIONSCAROUSEL
        validarTime()
    }

    fun promotions(){
        viewModelScope.launch {
            promotionsLiveData.postValue(Resource.OnLoader())
            try {
                var response = homeRepository.getPromotions()
                LogUtils().v(TAG, response.toString())
                promotionsLiveData.postValue(Resource.OnSuccess(response ?: arrayListOf()))
                LogUtils().v("promotionsLiveData", "${response ?: arrayListOf()}")

            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> promotionsLiveData.postValue(Resource.OnError("Network Failure"))
                    else -> promotionsLiveData.postValue(Resource.OnError("Conversion Error"))
                }
            }
        }
    }

    fun promotionsCarousel(){
        viewModelScope.launch {
            promotionsCarouselLiveData.postValue(Resource.OnLoader())
            try {
                var response = homeRepository.getPromotionsCarousel()
                LogUtils().v(TAG, response.toString())
                promotionsCarouselLiveData.postValue(Resource.OnSuccess(response ?: arrayListOf()))
                LogUtils().v("promotionsLiveData", "${response ?: arrayListOf()}")

            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> promotionsCarouselLiveData.postValue(Resource.OnError("Network Failure"))
                    else -> promotionsCarouselLiveData.postValue(Resource.OnError("Conversion Error"))
                }
            }
        }
    }

    fun tokenRefresh() {
        viewModelScope.launch {
            oauthTokenLiveData.postValue(Resource.OnLoader())
            try {
                LogUtils().v("tokenRefresh-timestamp", "${sharedPref?.getString(TIMESTAMP)}")
                LogUtils().v("getSha256Token", "${getSha256Token("${sharedPref?.getString(TIMESTAMP)}", "u8NcrvNDjM5JfDfD", "r008nO6NDosK0t6nfccMG0IEMwlcvwBK")}")
                var response = tokenRepository.tokenRefresh(
                    getSha256Token(
                        "${
                            sharedPref?.getString(
                                TIMESTAMP
                            )
                        }", "u8NcrvNDjM5JfDfD", "r008nO6NDosK0t6nfccMG0IEMwlcvwBK"
                    ), getRequestBody()
                )
                oauthTokenLiveData.postValue(Resource.OnSuccess(response))
                LogUtils().v("oauthTokenLiveData", "${response}")
                sharedPref.putValue(TOKEN_REWARD, response.accessToken.toString())
                when(nameApi){
                    PROMOTIONS -> promotions()
                    PROMOTIONSCAROUSEL -> promotionsCarousel()
                }
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> oauthTokenLiveData.postValue(Resource.OnError("Network Failure"))
                    else -> oauthTokenLiveData.postValue(Resource.OnError("Conversion Error"))
                }
            }
        }
    }


    fun validarTime() {
        val currentDate = Date()
        val dateDeviceStorage = GHServiceConfig.getDateDevice(
            context = contexts
        ) ?: currentDate

        var timestamp = SBBaseProcess().getServerCurrentTimeStamp(contexts)
        //sharedPref.putValue(TIMESTAMP, timestamp)
        LogUtils().v("currentDate", "${currentDate.time}")
        LogUtils().v("timestamp:::", "$timestamp")
        LogUtils().v("dateSave:::", "${sharedPref?.getString(TIMESTAMP)}")
        //LogUtils().v("dateSave:::", "1675491000")
        // val completed = currentDate.time - dateSave.time
        val seconds = (timestamp.toLong() - (sharedPref?.getString(TIMESTAMP)?.toLong() ?: 0))

        LogUtils().v("SECONDS:::", "$seconds")
        if (seconds < 0) {
            //createOauthLoginRefreshTokenServices
            LogUtils().v("SECONDS:::", "RefreshToken")
            sharedPref.putValue(TIMESTAMP, timestamp)
            tokenRefresh()
        } else {
            if (seconds == 0L || seconds >= 3240) {
                //createOauthLoginRefreshTokenServices
                LogUtils().v("SECONDS:::", "RefreshToken")
                sharedPref.putValue(TIMESTAMP, timestamp)
                tokenRefresh()
            } else {
                //accessTokenLogin
                LogUtils().v("SECONDS:::", "Usar Token")
                when(nameApi){
                    PROMOTIONS -> promotions()
                    PROMOTIONSCAROUSEL -> promotionsCarousel()
                }
            }
        }
    }

    fun getSha256Token(
        timestamp: String,
        clientId: String,
        clientSecret: String
    ): String {
        val sig = this.sha256("$clientId$clientSecret$timestamp")
        return sig
    }

    private fun sha256(token: String): String {
        return hashString(token, "SHA-256")
    }

    private fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }

    private fun getRequestBody(): RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("client_id", "u8NcrvNDjM5JfDfD")
            .addFormDataPart("client_secret", "r008nO6NDosK0t6nfccMG0IEMwlcvwBK")
            .addFormDataPart("grant_type", "client_credentials")
            .addFormDataPart("timestamp", "${sharedPref?.getString(TIMESTAMP)}")
            .build()



}