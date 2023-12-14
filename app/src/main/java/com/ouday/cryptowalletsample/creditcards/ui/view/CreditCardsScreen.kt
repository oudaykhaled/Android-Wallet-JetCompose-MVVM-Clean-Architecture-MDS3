package com.ouday.cryptowalletsample.creditcards.ui.view

import CreditCardComposable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.HandleFlowState
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.ui.viewmodel.CreditCardViewModel
import com.ouday.cryptowalletsample.home.components.LoanCardComposable
import com.ouday.cryptowalletsample.home.ui.view.CreditCardsPager
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.craneColors

@Composable
fun CreditCardsScreen(modifier: Modifier = Modifier) {
    val viewModel: CreditCardViewModel = hiltViewModel()
    val creditCardState by viewModel.creditCards.collectAsState(initial = FlowState.Loading)

    LaunchedEffect(Unit) {
        viewModel.triggerFetchCreditCards()
    }

    HandleFlowState(
        flowState = creditCardState,
        onSuccess = { creditCards ->
            CreditCardsList(creditCards)
        },
        onRetry = {
            viewModel.triggerFetchCreditCards()
        }
    )
}

@Composable
fun CreditCardsList(creditCards: List<CreditCardInfo>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn {
            items(creditCards.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = Space.spaceSmall, vertical = Space.spaceSmall)
                ) {
                    CreditCardComposable(
                        creditCards[index],
                        modifier = Modifier
                    )
                }
            }
        }
    }
}