package com.ouday.cryptowalletsample.creditcards.data.model

import javax.annotation.concurrent.Immutable

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Immutable
@JsonClass(generateAdapter = true)
data class CreditCardInfo(
    @Json(name = "cardType") val cardType: String,
    @Json(name = "cardNumber") val cardNumber: String,
    @Json(name = "dueDate") val dueDate: String,
    @Json(name = "amountDue") val amountDue: String,
    @Json(name = "paymentStatus") val paymentStatus: String
)
