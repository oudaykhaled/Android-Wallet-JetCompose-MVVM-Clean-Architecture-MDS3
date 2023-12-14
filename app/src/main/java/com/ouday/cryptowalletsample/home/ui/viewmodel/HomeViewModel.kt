package com.ouday.cryptowalletsample.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.asFlowState
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCase
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.usecase.SubscriptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(billUseCase: BillUseCase, creditCarUseCase: CreditCardUseCase, subscriptionUseCase: SubscriptionUseCase): ViewModel() {
    private val _retryFetchCreditCards = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    private val _retryFetchBills = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    private val _retryFetchSubscription = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    val bills : Flow<FlowState<List<Bill>>> = _retryFetchBills
        .flatMapLatest { billUseCase.getBills().asFlowState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, FlowState.Loading)

    val creditCards: Flow<FlowState<List<CreditCardInfo>>> = _retryFetchCreditCards
        .flatMapLatest { creditCarUseCase.getCreditCards().asFlowState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, FlowState.Loading)


    val subscriptions: Flow<FlowState<List<Subscription>>> = _retryFetchSubscription
        .flatMapLatest { subscriptionUseCase.getCreditCards().asFlowState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, FlowState.Loading)

    fun triggerFetchCreditCards() {
        viewModelScope.launch {
            _retryFetchCreditCards.emit(Unit)
        }
    }

    fun triggerFetchBills() {
        viewModelScope.launch {
            _retryFetchBills.emit(Unit)
        }
    }

    fun triggerFetchSubscriptions() {
        viewModelScope.launch {
            _retryFetchSubscription.emit(Unit)
        }
    }
}