package com.ouday.cryptowalletsample.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.ui.theme.Colors
import com.ouday.cryptowalletsample.ui.theme.MaterialCornerRadius
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.Typography

@Composable
fun CreditCardComposable(cardInfo: CreditCardInfo, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(Size.sizeXMax) // Using Size from CryptoWalletSampleTheme
            .clip(RoundedCornerShape(MaterialCornerRadius.radiusMedium)) // Using MaterialCornerRadius from CryptoWalletSampleTheme
            .background(Colors.surface) // Using Colors from CryptoWalletSampleTheme
            .padding(Space.spaceMedium) // Using Space from CryptoWalletSampleTheme
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (cardType, cardNumber, dueDate, amount, payButton, paymentStatus) = createRefs()

            Text(
                text = cardInfo.cardType,
                color = Colors.onSurface, 
                style = Typography.h6, 
                modifier = Modifier.constrainAs(cardType) {
                    top.linkTo(parent.top, margin = Space.spaceMedium)
                    start.linkTo(parent.start, margin = Space.spaceMedium)
                }
            )

            Text(
                text = cardInfo.cardNumber, // Masked number for privacy
                color = Colors.onSurface, 
                style = Typography.h6, 
                modifier = Modifier.constrainAs(cardNumber) {
                    top.linkTo(cardType.bottom, margin = Space.spaceSmall)
                    end.linkTo(parent.end, margin = Space.spaceMedium)
                }
            )

            Text(
                text = stringResource(R.string.due_date, cardInfo.dueDate),
                color = Colors.onSurface, 
                style = Typography.subtitle1, 
                modifier = Modifier.constrainAs(dueDate) {
                    top.linkTo(cardNumber.bottom, margin = Space.spaceSmall)
                    start.linkTo(parent.start, margin = Space.spaceMedium)
                }
            )

            Text(
                text = cardInfo.amountDue,
                color = Colors.onSurface, 
                style = Typography.h4.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.constrainAs(amount) {
                    top.linkTo(dueDate.bottom, margin = Space.spaceSmall)
                    start.linkTo(parent.start, margin = Space.spaceMedium)
                }
            )

            Button(
                onClick = { /* Handle the payment action */ },
                modifier = Modifier.constrainAs(payButton) {
                    bottom.linkTo(parent.bottom, margin = Space.spaceMedium)
                    end.linkTo(parent.end, margin = Space.spaceMedium)
                }
            ) {
                Text(
                    text = stringResource(R.string.pay),
                    style = Typography.button, 
                    color = Colors.onSurface
                )
            }

            Text(
                text = cardInfo.paymentStatus,
                color = Colors.onSurface, 
                style = Typography.overline, 
                modifier = Modifier.constrainAs(paymentStatus) {
                    start.linkTo(parent.start, margin = Space.spaceMedium)
                    bottom.linkTo(payButton.top, margin = Space.spaceSmall)
                }
            )
        }
    }
}