package com.ouday.cryptowalletsample.subscriptions.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.HandleFlowState
import com.ouday.cryptowalletsample.home.components.LoanCardComposable
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.ui.viewmodel.SubscriptionViewModel
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.craneColors

@Composable
fun SubscriptionScreen(modifier: Modifier = Modifier, viewModel: SubscriptionViewModel = hiltViewModel()) {
    val subscriptionState by viewModel.subscriptions.collectAsState(initial = FlowState.Loading)

    LaunchedEffect(Unit) {
        viewModel.triggerFetchSubscription()
    }

    HandleFlowState(
        flowState = subscriptionState,
        onSuccess = { subscriptionsList ->
            SubscriptionsList(subscriptionsList)
        },
        onRetry = {
            viewModel.triggerFetchSubscription()
        })
}

@Composable
fun SubscriptionsList(subscriptions: List<Subscription>, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn {
            items(subscriptions.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = Space.spaceSmall, vertical = Space.spaceSmall)
                ) {
                    LoanCardComposable(
                        subscriptions[index],
                        Modifier
                            .fillMaxWidth()
                            .height(Size.sizeMax)
                            .clip(RoundedCornerShape(Size.sizeSmall))
                            .background(craneColors.primary.copy(alpha = 0.2f))
                            .padding(Size.sizeMedium)
                    )
                }
            }
        }
    }
}