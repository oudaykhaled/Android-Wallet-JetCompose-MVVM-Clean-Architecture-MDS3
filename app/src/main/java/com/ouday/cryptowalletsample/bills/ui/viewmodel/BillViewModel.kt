package com.ouday.cryptowalletsample.bills.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.asFlowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(val billUseCase: BillUseCase) : ViewModel() {
    public val _retryFetchBills = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val bills: Flow<FlowState<List<Bill>>> = _retryFetchBills
        .flatMapLatest { billUseCase.getBills().asFlowState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, FlowState.Loading)

    fun triggerFetchBills() {
        viewModelScope.launch {
            _retryFetchBills.emit(Unit)
        }
    }
}