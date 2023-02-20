package com.starbucks.peru.domain.response

import com.google.gson.annotations.SerializedName

data class SBPromotionsCarouselModel(
    @SerializedName("name")
    var title: String? = "",
    @SerializedName("url")
    var fieldImage: String = "",
    var nid: Int? = 0,
    var order: Int? = 0
) {
}