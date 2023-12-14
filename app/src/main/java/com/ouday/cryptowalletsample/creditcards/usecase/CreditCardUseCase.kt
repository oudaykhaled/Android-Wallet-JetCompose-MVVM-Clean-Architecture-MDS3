package com.ouday.cryptowalletsample.creditcards.usecase

import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import kotlinx.coroutines.flow.Flow

interface CreditCardUseCase {
    fun getCreditCards(): Flow<List<CreditCardInfo>>
}