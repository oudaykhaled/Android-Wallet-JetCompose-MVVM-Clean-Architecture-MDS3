package com.ouday.cryptowalletsample.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.ui.theme.MaterialCornerRadius
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.Typography

@Composable
fun CreditCardComposable(cardInfo: CreditCardInfo, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(Size.sizeXMax)
            .clip(RoundedCornerShape(MaterialCornerRadius.radiusMedium))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(Space.spaceMedium)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (cardType, cardNumber, dueDate, amount, payButton, paymentStatus) = createRefs()

            Text(
                text = cardInfo.cardType,
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.h6,
                modifier = Modifier.constrainAs(cardType) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            )

            Text(
                text = cardInfo.cardNumber, // Masked number for privacy
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.h6,
                modifier = Modifier.constrainAs(cardNumber) {
                    top.linkTo(cardType.bottom)
                    end.linkTo(parent.end, margin = Space.spaceMedium)
                }
            )

            Text(
                text = stringResource(R.string.due_date, cardInfo.dueDate),
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.subtitle1,
                modifier = Modifier.constrainAs(dueDate) {
                    top.linkTo(cardNumber.bottom)
                    start.linkTo(parent.start)
                }
            )

            Text(
                text = cardInfo.amountDue,
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.h4.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.constrainAs(amount) {
                    top.linkTo(paymentStatus.bottom)
                    start.linkTo(parent.start)
                }
            )

            Button(
                onClick = { /*TODO Handle on card clicked */ },
                modifier = Modifier
                    .constrainAs(payButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
            ) {
                Text(
                    text = stringResource(R.string.pay),
                    style = Typography.button,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                text = cardInfo.paymentStatus,
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.overline,
                modifier = Modifier
                    .constrainAs(paymentStatus) {
                    start.linkTo(parent.start)
                    bottom.linkTo(payButton.top)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreditCardComposable() {
    // Sample CreditCardInfo data for the preview
    val sampleCreditCardInfo = CreditCardInfo(
        cardType = "Visa",
        cardNumber = "**** **** **** 1234",
        dueDate = "12/25/2023",
        amountDue = "$1,200.00",
        paymentStatus = "Pending"
    )

    // Preview of CreditCardComposable
    CreditCardComposable(
        cardInfo = sampleCreditCardInfo,
        modifier = Modifier.padding(Space.spaceMedium)
    )
}
