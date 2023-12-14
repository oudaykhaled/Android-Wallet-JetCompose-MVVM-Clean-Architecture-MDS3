package com.ouday.cryptowalletsample.creditcards.data.repository

import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import kotlinx.coroutines.flow.Flow

interface CreditCardRepository {
    fun getCreditCards(): Flow<List<CreditCardInfo>>
}