package com.ouday.cryptowalletsample.creditcards.data.remote

import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import retrofit2.Response
import retrofit2.http.GET

interface CreditCardApiService {
    @GET("credit-cards")
    suspend fun getCreditCards(): Response<List<CreditCardInfo>>
}
