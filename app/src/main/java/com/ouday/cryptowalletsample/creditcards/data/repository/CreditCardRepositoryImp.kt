package com.ouday.cryptowalletsample.creditcards.data.repository

import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.data.remote.CreditCardApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreditCardRepositoryImp @Inject constructor(val creditCardApiService: CreditCardApiService) :
    CreditCardRepository {
    override fun getCreditCards(): Flow<List<CreditCardInfo>> = flow {
        emit(creditCardApiService.getCreditCards().body() ?: emptyList())
    }.catch { e -> throw e }
}