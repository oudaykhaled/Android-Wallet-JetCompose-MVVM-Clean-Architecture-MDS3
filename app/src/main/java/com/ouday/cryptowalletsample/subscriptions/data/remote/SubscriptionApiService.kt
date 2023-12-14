package com.ouday.cryptowalletsample.subscriptions.data.remote

import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import retrofit2.Response
import retrofit2.http.GET

interface SubscriptionApiService {
    @GET("subscriptions")
    suspend fun getSubscriptions(): Response<List<Subscription>>
}