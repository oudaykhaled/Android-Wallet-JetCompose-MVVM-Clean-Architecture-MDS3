package com.ouday.cryptowalletsample.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.craneColors

@Composable
fun LoanCardComposable(
    content: Subscription,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (imageBox, price, model, progress, nextBilling, billingDate, progressTextRef) = createRefs()

            CardImageBox(
                imageResourceId = R.drawable.ic_car,
                imageContentDescription = content.imageContentDescription,
                imageBoxRef = imageBox
            )

            CardPriceText(
                priceText = content.priceText,
                priceRef = price
            )

            CardModelText(
                modelText = content.modelText,
                modelRef = model,
                imageBoxRef = imageBox
            )

            CardProgressIndicator(
                progressFraction = content.progressFraction.toFloat(),
                progressRef = progress,
                modelRef = model
            )

            CardNextBillingText(
                nextText = content.nextText,
                nextBillingRef = nextBilling,
                priceRef = price
            )

            CardBillingDateText(
                billingDateText = content.billingDateText,
                billingDateRef = billingDate,
                nextBillingRef = nextBilling
            )

            CardProgressText(
                progressText = content.progressText,
                progressTextRef = progressTextRef,
                progressRef = progress
            )
        }
    }
}

@Composable
fun ConstraintLayoutScope.CardImageBox(
    imageResourceId: Int,
    imageContentDescription: String,
    imageBoxRef: ConstrainedLayoutReference
) {
    // Implementation for CardImageBox
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .constrainAs(imageBoxRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = imageContentDescription,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ConstraintLayoutScope.CardPriceText(
    priceText: String,
    priceRef: ConstrainedLayoutReference
) {
    // Implementation for CardPriceText
    Text(
        text = priceText,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.constrainAs(priceRef) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }
    )
}

@Composable
fun ConstraintLayoutScope.CardModelText(
    modelText: String,
    modelRef: ConstrainedLayoutReference,
    imageBoxRef: ConstrainedLayoutReference
) {
    Text(
        text = modelText,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(modelRef) {
            top.linkTo(imageBoxRef.bottom, margin = 8.dp)
            start.linkTo(parent.start)
        }
    )
}

@Composable
fun ConstraintLayoutScope.CardProgressIndicator(
    progressFraction: Float,
    progressRef: ConstrainedLayoutReference,
    modelRef: ConstrainedLayoutReference
) {
    LinearProgressIndicator(
        progress = progressFraction,
        backgroundColor = Color.LightGray,
        color = Color.Blue,
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .constrainAs(progressRef) {
                top.linkTo(modelRef.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
    )
}

@Composable
fun ConstraintLayoutScope.CardNextBillingText(
    nextText: String,
    nextBillingRef: ConstrainedLayoutReference,
    priceRef: ConstrainedLayoutReference
) {
    Text(
        text = nextText,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.constrainAs(nextBillingRef) {
            top.linkTo(priceRef.bottom, margin = 4.dp)
            end.linkTo(parent.end)
        }
    )
}

@Composable
fun ConstraintLayoutScope.CardBillingDateText(
    billingDateText: String,
    billingDateRef: ConstrainedLayoutReference,
    nextBillingRef: ConstrainedLayoutReference
) {
    Text(
        text = billingDateText,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.constrainAs(billingDateRef) {
            top.linkTo(nextBillingRef.bottom, margin = 4.dp)
            end.linkTo(parent.end)
        }
    )
}

@Composable
fun ConstraintLayoutScope.CardProgressText(
    progressText: String,
    progressTextRef: ConstrainedLayoutReference,
    progressRef: ConstrainedLayoutReference
) {
    Text(
        text = progressText,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.constrainAs(progressTextRef) {
            top.linkTo(progressRef.top)
            bottom.linkTo(progressRef.bottom)
            end.linkTo(progressRef.end)
        }
    )
}