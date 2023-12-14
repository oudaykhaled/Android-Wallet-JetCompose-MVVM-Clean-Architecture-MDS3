package com.ouday.cryptowalletsample.creditcards.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.asFlowState
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreditCardViewModel @Inject constructor(creditCarUseCase: CreditCardUseCase) : ViewModel() {
    private val _retryFetchCreditCards = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val creditCards: Flow<FlowState<List<CreditCardInfo>>> = _retryFetchCreditCards
        .flatMapLatest { creditCarUseCase.getCreditCards().asFlowState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, FlowState.Loading)

    fun triggerFetchCreditCards() {
        viewModelScope.launch {
            _retryFetchCreditCards.emit(Unit)
        }
    }
}