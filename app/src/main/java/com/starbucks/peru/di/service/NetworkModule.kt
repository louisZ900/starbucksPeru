package com.starbucks.peru.di.service

import android.app.Application
import android.content.Context
import com.starbucks.peru.core.preferences.SBSharedPreferences
import com.starbucks.peru.core.utils.LogUtils
import com.starbucks.peru.core.utils.SBBaseProcess
import com.starbucks.peru.data.Ecommerce
import com.starbucks.peru.data.EcommerceApiService
import com.starbucks.peru.data.Reward
import com.starbucks.peru.data.RewardApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

   // private var prefs: SharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
   // private var prefs2: SharedPreferences = SharedPreferences.getString(key, "")

    //var sharedPref: SBSharedPreferences? = null
    var sharedPref: SBSharedPreferences? = null

    const val USER_TOKEN = "user_token"


/*    @Provides
    fun providesShared(context: Application): SharedPreferences{
        return context.getSharedPreferences("DB1", Context.MODE_PRIVATE)
    }*/

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor, sbSharedPreferences: SBSharedPreferences): OkHttpClient {
        sharedPref = sbSharedPreferences
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(providerHttpLoggingInterceptor())
            //.addInterceptor(HeaderOAuthInterceptor())
            //.authenticator(TokenAuthenticator())
            .build()
    }


    @Provides
    @Singleton
    @Ecommerce
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        LogUtils().v("BASE_URL:::", "${sharedPref?.getString("BASE_URL")}")
        return Retrofit.Builder()
            .baseUrl(EcommerceApiService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
                    .readTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(providerHttpLoggingInterceptor())
                    .addInterceptor(HeaderEcommerceInterceptor())
                    //.authenticator(TokenAuthenticator())
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    @Reward   //This will differentiate retrofit object
    fun retrofitSetting(client: OkHttpClient): Retrofit {
        LogUtils().v("BASE_URL","${sharedPref?.getString("BASE_URL")}")
        return Retrofit.Builder()
            .baseUrl(RewardApiService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
                    .readTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(providerHttpLoggingInterceptor())
                    .addInterceptor(HeaderRewardInterceptor())
                    //.authenticator(TokenAuthenticator())
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    fun EcommerceprovideAppService(@Ecommerce retrofit: Retrofit): EcommerceApiService {
        return retrofit.create(EcommerceApiService::class.java)
    }

    @Provides
    @Singleton
    fun RewardprovideAppService(@Reward retrofit: Retrofit): RewardApiService {
        return retrofit.create(RewardApiService::class.java)
    }

    private fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        return logging
    }


    class HeaderEcommerceInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
            proceed(
                request()
                    .newBuilder()
                    .header("Token", "${sharedPref?.getString(TOKEN_VALUE)}")
                    .header("IdEjecucion", "12345678")
                    .header("IdMarca", "121")
                    .header("Content-Type", "application/json")
                    .build()
            )
        }
    }
    class HeaderRewardInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
            proceed(
                request()
                    .newBuilder()
                    .header("Content-Type", "application/json")
                    .header("x-api-key", "u8NcrvNDjM5JfDfD")
                    .build()
            )
        }
    }

    class TokenAuthenticator : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
            LogUtils().v("TOKEN_VALUE::","${sharedPref?.getString(TOKEN_VALUE)}")
            return try {
                val updatedToken = getNewToken()
                response.request.newBuilder()
                    .header("Token", "$updatedToken")
                    .build()
            }catch (e: Exception){
                response.request.newBuilder()
                    .header("Token", "fail")
                    .build()
            }
        }
    }

    private fun getNewToken(): String? {
        LogUtils().v("REFRESH_TOKEN:::", "")


        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("client_id", "u8NcrvNDjM5JfDfD")
            .addFormDataPart("client_secret", "r008nO6NDosK0t6nfccMG0IEMwlcvwBK")
            .addFormDataPart("grant_type", GRANT_TYPE_SIGN_OFF)
            .addFormDataPart("timestamp", "${sharedPref?.getString(TIMESTAMP)}")
            .build()

        val retrofitResponse = tokenRefreshInterface().tokenRefreshV2(getSha256Token("${sharedPref?.getString(TIMESTAMP)}", "u8NcrvNDjM5JfDfD", "r008nO6NDosK0t6nfccMG0IEMwlcvwBK"), requestBody).execute().body()
        //sharedPref?.putValue(TOKEN_VALUE,retrofitResponse?.Data?.Token ?: "")
        LogUtils().v("getSha256Token:::", "${getSha256Token("1675395005", "u8NcrvNDjM5JfDfD", "r008nO6NDosK0t6nfccMG0IEMwlcvwBK")}")
        LogUtils().v("REFRESH_ECO:::new", "${retrofitResponse?.accessToken}")
        sharedPref?.putValue(TOKEN_VALUE, "${retrofitResponse?.accessToken}")

        return retrofitResponse?.accessToken
    }


    var appApiOAuth: RewardApiService? = null

    fun tokenRefreshInterface(): RewardApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(RewardApiService.ENDPOINT)
            .client(getTokenClientInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        appApiOAuth = retrofit.create(RewardApiService::class.java)
        return appApiOAuth as RewardApiService
    }

    fun getTokenClientInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(providerHttpLoggingInterceptor())
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
            proceed(
                request()
                    .newBuilder()
                    .header("x-api-key", "u8NcrvNDjM5JfDfD")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build()
            )
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
}