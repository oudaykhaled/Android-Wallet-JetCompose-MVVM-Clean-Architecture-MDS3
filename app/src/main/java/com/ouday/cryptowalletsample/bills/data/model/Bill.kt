package com.ouday.cryptowalletsample.bills.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bill(
    @Json(name = "id") val id: Int,
    @Json(name = "billName") val billName: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "history") val history: List<History>
)
