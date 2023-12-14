package com.ouday.cryptowalletsample.bills.domain.usecase

import com.ouday.cryptowalletsample.bills.data.model.Bill
import kotlinx.coroutines.flow.Flow

interface BillUseCase {
    fun getBills(): Flow<List<Bill>>
}