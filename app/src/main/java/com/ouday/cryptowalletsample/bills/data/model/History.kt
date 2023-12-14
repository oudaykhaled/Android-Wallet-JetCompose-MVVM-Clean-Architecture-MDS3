package com.ouday.cryptowalletsample.bills.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class History(
    @Json(name = "date") val date: String,
    @Json(name = "amount") val amount: Double,
    @Json(name = "paid") val paid: Boolean
)