package com.starbucks.peru.domain.response

import com.google.gson.annotations.SerializedName

data class SBOauthTokenNoLoginModel(
    @SerializedName("return_type")
    var returnType: String? = "",
    @SerializedName("access_token")
    var accessToken: String? = "",
    @SerializedName("token_type")
    var tokenType: String? = "",
    @SerializedName("expires_in")
    var expiresIn: Int? = 0
) {
}