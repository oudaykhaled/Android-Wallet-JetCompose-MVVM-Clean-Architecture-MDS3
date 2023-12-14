package com.ouday.cryptowalletsample.subscriptions.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Subscription(
    @Json(name = "priceText") val priceText: String,
    @Json(name = "modelText") val modelText: String,
    @Json(name = "nextText") val nextText: String,
    @Json(name = "billingDateText") val billingDateText: String,
    @Json(name = "progressText") val progressText: String,
    @Json(name = "progressFraction") val progressFraction: Double,
    @Json(name = "imageContentDescription") val imageContentDescription: String,
    @Json(name = "imageResourceId") val imageResourceId: String
)
