package com.ouday.cryptowalletsample.bills.data.remote

import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import retrofit2.Response
import retrofit2.http.GET

interface BillApiService {
    @GET("bills")
    suspend fun getCreditCards(): Response<List<Bill>>
}
