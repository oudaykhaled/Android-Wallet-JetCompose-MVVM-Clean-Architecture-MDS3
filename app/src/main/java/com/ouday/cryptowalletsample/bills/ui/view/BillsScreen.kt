package com.ouday.cryptowalletsample.bills.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.model.History
import com.ouday.cryptowalletsample.bills.ui.viewmodel.BillViewModel
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.HandleFlowState
import com.ouday.cryptowalletsample.home.components.BillOptionItem
import com.ouday.cryptowalletsample.ui.theme.Colors
import com.ouday.cryptowalletsample.ui.theme.MaterialCornerRadius
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.Typography
import com.ouday.cryptowalletsample.ui.theme.success

@Composable
fun BillsScreen(modifier: Modifier = Modifier, viewModel: BillViewModel = hiltViewModel()) {
    var selectedBill by remember { mutableStateOf<Bill?>(null) }
    val bills by viewModel.bills.collectAsState(initial = FlowState.Loading)
    LaunchedEffect(Unit) {
        viewModel.triggerFetchBills()
    }
    HandleFlowState(
        flowState = bills,
        onSuccess = { billsList ->
            Column {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    billsList.forEach { option ->
                        BillOptionItem(option, option == selectedBill) {
                            selectedBill = it
                        }
                    }
                    if (selectedBill == null && billsList.isNotEmpty()) {
                        selectedBill = billsList[0]
                    }
                }
                selectedBill?.history?.let { history ->
                    LazyColumn {
                        items(history.size) { index ->
                            BillDetails(history[index])
                        }
                    }
                }
            }
        },
        onRetry = {
            viewModel.triggerFetchBills()
        }
    )
}

@Composable
fun BillDetails(history: History, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Space.spaceSmall, vertical = Space.spaceSmall)
            .clip(RoundedCornerShape(MaterialCornerRadius.radiusMedium))
            .background(Colors.primaryVariant)
            .padding(Space.spaceMedium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(
                    R.string.bill_date,
                    history.date
                ),
                style = Typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Colors.onSurface 
            )
            Text(
                text = stringResource(
                    R.string.bill_amount,
                    history.amount
                ),
                style = Typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Colors.onSurface 
            )
        }
        Box(
            modifier = Modifier
                .width(Size.size3XLarge)
                .size(Space.spaceLarge)
                .clip(CircleShape)
                .background(if (history.paid) Colors.success else Colors.error),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (history.paid) stringResource(R.string.paid) else stringResource(R.string.not_paid),
                color = Colors.onSurface,
                style = Typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
