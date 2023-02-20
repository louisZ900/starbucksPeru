package com.starbucks.peru.domain.request

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

data class SBOauthTokenNoLoginRequestEntity(
    var grantType: String?    = "",
    var clientId: String?     = "",
    var clientSecret: String? = "",
    var timestamp: String?    = ""
) {
    fun toJson(): String {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .disableHtmlEscaping().create().toJson(this)
    }
}