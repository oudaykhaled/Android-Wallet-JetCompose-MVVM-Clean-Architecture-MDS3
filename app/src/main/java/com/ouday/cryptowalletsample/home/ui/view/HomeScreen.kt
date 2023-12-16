package com.ouday.cryptowalletsample.home.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.HandleFlowState
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.home.components.BillOptionItem
import com.ouday.cryptowalletsample.home.components.CreditCardComposable
import com.ouday.cryptowalletsample.home.components.LoanCardComposable
import com.ouday.cryptowalletsample.home.ui.viewmodel.HomeViewModel
import com.ouday.cryptowalletsample.ui.theme.*

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {

            CreditCards(viewModel = viewModel)
            Spacer(modifier = Modifier.height(Space.spaceMedium))
            Text(
                text = stringResource(id = R.string.bill_payments),
                style = Typography.subtitle1,
                modifier = Modifier.padding(start = Space.spaceLarge),
                color = Colors.onSurface
            )
            Spacer(modifier = Modifier.height(Space.spaceSmall))
            BillOptionsComposable(viewModel = viewModel)
            Spacer(modifier = Modifier.height(Space.spaceSmall))
            Text(
                text = stringResource(id = R.string.active_loans),
                style = Typography.subtitle1,
                modifier = Modifier.padding(start = Space.spaceLarge),
                color = Colors.onSurface
            )
            Spacer(modifier = Modifier.height(Space.spaceSmall))
            Subscriptions(viewModel = viewModel)
        }
    }
}

@Composable
fun Subscriptions(viewModel: HomeViewModel = hiltViewModel()) {
    val subscriptionState by viewModel.subscriptions.collectAsState(initial = FlowState.Loading)
    LaunchedEffect(Unit) {
        viewModel.triggerFetchSubscriptions()
    }
    HandleFlowState(
        flowState = subscriptionState,
        onSuccess = { subscriptions ->
            LazyRow {
                items(subscriptions.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = Space.spaceSmall, vertical = 0.dp)
                    ) {
                        LoanCardComposable(
                            subscriptions[index],
                            Modifier
                                .width(Size.size2XMax)
                                .height(Size.sizeMax)
                                .clip(RoundedCornerShape(Size.sizeSmall))
                                .background(Colors.primary.copy(alpha = 0.2f))
                                .padding(Size.sizeMedium)
                        )
                    }
                }
            }
        },
        onRetry = {
            viewModel.triggerFetchSubscriptions()
        }
    )
}

@Composable
fun Test() {
}

@Composable
fun CreditCards(viewModel: HomeViewModel = hiltViewModel()) {
    val creditCardState by viewModel.creditCards.collectAsState(initial = FlowState.Loading)

    LaunchedEffect(Unit) {
        viewModel.triggerFetchCreditCards()
    }

    HandleFlowState(
        flowState = creditCardState,
        onSuccess = { creditCards ->
            CreditCardsPager(creditCards)
        },
        onRetry = {
            viewModel.triggerFetchCreditCards()
        }
    )
}

@Composable
fun BillOptionsComposable(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val bills by viewModel.bills.collectAsState(initial = FlowState.Loading)
    LaunchedEffect(Unit) {
        viewModel.triggerFetchBills()
    }
    HandleFlowState(
        flowState = bills,
        onSuccess = { billsList ->
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                billsList.forEach { option ->
                    BillOptionItem(option, false){}
                }
            }
        },
        onRetry = {
            viewModel.triggerFetchBills()
        }
    )
}

@Composable
fun CreditCardsPager(creditCards: List<CreditCardInfo>) {
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            count = creditCards.size,
            state = pagerState,
            modifier = Modifier
                .height(Size.sizeXMax)
                .padding(Space.spaceMedium)
        ) { page ->
            CreditCardComposable(
                cardInfo = creditCards[page],
                Modifier.padding(Space.spaceXSmall, 0.dp, Space.spaceXSmall, 0.dp)
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            activeColor = Colors.primary,
            inactiveColor = Colors.primary.copy(alpha = 0.6f)
        )
    }
}
