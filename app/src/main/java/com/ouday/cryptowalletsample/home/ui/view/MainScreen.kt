package com.ouday.cryptowalletsample.home.ui.view

import BottomNavItem
import BottomNavigationBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.bills.ui.view.BillsScreen
import com.ouday.cryptowalletsample.creditcards.ui.view.CreditCardsScreen
import com.ouday.cryptowalletsample.home.components.CardBillingDateText
import com.ouday.cryptowalletsample.home.components.CardImageBox
import com.ouday.cryptowalletsample.home.components.CardModelText
import com.ouday.cryptowalletsample.home.components.CardNextBillingText
import com.ouday.cryptowalletsample.home.components.CardPriceText
import com.ouday.cryptowalletsample.home.components.CardProgressIndicator
import com.ouday.cryptowalletsample.home.components.CardProgressText
import com.ouday.cryptowalletsample.home.components.HomeHeaderComposable
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.ui.view.SubscriptionScreen
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.craneColors

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Search,
                    BottomNavItem.Wallet,
                    BottomNavItem.Profile,
                ),
                "home"
            ) { selectedItem ->
                navController.navigate(selectedItem.route)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            HomeHeaderComposable(
                "Ouday",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            NavHost(navController, startDestination = BottomNavItem.Home.route) {
                composable(BottomNavItem.Home.route) { HomeScreen() }
                composable(BottomNavItem.Search.route) { BillsScreen() }
                composable(BottomNavItem.Wallet.route) { CreditCardsScreen() }
                composable(BottomNavItem.Profile.route) { SubscriptionScreen() }
            }
        }
    }
}