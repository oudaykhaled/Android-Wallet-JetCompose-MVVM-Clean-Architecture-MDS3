package com.ouday.cryptowalletsample.subscriptions.data.repository

import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SubscriptionRepository {
    fun getSubscriptions(): Flow<List<Subscription>>
}