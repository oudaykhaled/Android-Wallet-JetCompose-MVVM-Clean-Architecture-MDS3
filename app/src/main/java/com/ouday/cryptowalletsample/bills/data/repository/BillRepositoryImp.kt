package com.ouday.cryptowalletsample.bills.data.repository

import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.remote.BillApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BillRepositoryImp @Inject constructor(val apiServiceL: BillApiService) : BillRepository {
    override fun getBills(): Flow<List<Bill>> = flow {
        emit(apiServiceL.getCreditCards().body() ?: emptyList())
    }.catch { e -> throw e }
}