package com.ouday.cryptowalletsample.subscriptions.data.repository

import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.data.remote.SubscriptionApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubscriptionRepositoryImp  @Inject constructor(val apiServiceL: SubscriptionApiService) : SubscriptionRepository {
    override fun getSubscriptions(): Flow<List<Subscription>> = flow {
        emit(apiServiceL.getSubscriptions().body() ?: emptyList())
    }.catch { e -> throw e }
}