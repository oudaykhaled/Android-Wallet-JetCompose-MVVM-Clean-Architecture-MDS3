package com.ouday.cryptowalletsample.bills.data.repository

import com.ouday.cryptowalletsample.bills.data.model.Bill
import kotlinx.coroutines.flow.Flow

interface BillRepository {
    fun getBills(): Flow<List<Bill>>
}