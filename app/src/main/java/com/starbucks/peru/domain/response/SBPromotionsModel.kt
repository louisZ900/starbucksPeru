package com.starbucks.peru.domain.response

import com.google.gson.annotations.SerializedName

data class SBPromotionsModel(
    var nid: Int? = 0,
    var title: String? = "",

    @SerializedName("field_image")
    var fieldImage: String? = "",

    @SerializedName("field_fechainicio")
    var fieldStartDate: String? = "",

    @SerializedName("field_fechafin")
    var fieldEndDate: String? = "",

    var url: String? = "",
    @SerializedName("field_descripcion_corta")
    var fieldShortDescription: String? = "",
    var order: Int? = 0
) {
}