package com.ouday.cryptowalletsample.subscriptions.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.asFlowState
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
class SubscriptionViewModel @Inject constructor(subscriptionUseCase: SubscriptionUseCase) :
    ViewModel() {
    private val _retryFetchSubscription = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val subscriptions: Flow<FlowState<List<Subscription>>> = _retryFetchSubscription
        .flatMapLatest { subscriptionUseCase.getCreditCards().asFlowState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, FlowState.Loading)

    fun triggerFetchSubscription() {
        viewModelScope.launch {
            _retryFetchSubscription.emit(Unit)
        }
    }
}