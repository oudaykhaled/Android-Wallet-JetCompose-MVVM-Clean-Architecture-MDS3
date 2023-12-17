package com.ouday.cryptowalletsample.home.ui.view

import BottomNavItem
import BottomNavigationBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ouday.cryptowalletsample.bills.ui.view.BillsScreen
import com.ouday.cryptowalletsample.creditcards.ui.view.CreditCardsScreen
import com.ouday.cryptowalletsample.home.components.HomeHeaderComposable
import com.ouday.cryptowalletsample.subscriptions.ui.view.SubscriptionScreen

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
                )
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
                "Welcome Back!",
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