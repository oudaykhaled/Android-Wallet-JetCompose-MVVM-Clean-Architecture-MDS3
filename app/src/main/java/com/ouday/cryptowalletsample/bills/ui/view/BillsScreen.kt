package com.ouday.cryptowalletsample.bills.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.model.History
import com.ouday.cryptowalletsample.bills.ui.viewmodel.BillViewModel
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.common.HandleFlowState
import com.ouday.cryptowalletsample.home.components.BillOptionItem
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.craneColors
import com.ouday.cryptowalletsample.ui.theme.craneTypography
import com.ouday.cryptowalletsample.ui.theme.success

@Composable
fun BillsScreen(modifier: Modifier = Modifier) {
    val viewModel: BillViewModel = hiltViewModel()
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
            .clip(RoundedCornerShape(14.dp)) // First clip, then apply background
            .background(craneColors.primaryVariant)
            .padding(16.dp), // Padding goes after background
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Date: ${history.date}",
                style = craneTypography.body1
            )
            Text(
                text = "Amount: \$${history.amount}",
                style = craneTypography.body1
            )
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(if (history.paid) craneColors.success else craneColors.error),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (history.paid) "✓" else "✕",
                color = craneColors.background,
                fontSize = 12.sp
            )
        }
    }
}
