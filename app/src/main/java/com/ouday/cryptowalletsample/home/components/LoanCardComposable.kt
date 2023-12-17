package com.ouday.cryptowalletsample.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.ui.theme.*

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
                imageContentDescription = stringResource(
                    R.string.image_content_description,
                    content.imageContentDescription
                ),
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
    Box(
        modifier = Modifier
            .size(Size.size2XLarge)
            .background(Color.White, RoundedCornerShape(MaterialCornerRadius.radiusSmall))
            .constrainAs(imageBoxRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = imageContentDescription,
            modifier = Modifier
                .size(Size.sizeXLarge)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ConstraintLayoutScope.CardPriceText(
    priceText: String,
    priceRef: ConstrainedLayoutReference
) {
    Text(
        text = priceText,
        style = Typography.h6,
        color = MaterialTheme.colorScheme.onSurface,
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
        style = Typography.subtitle1,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.constrainAs(modelRef) {
            top.linkTo(imageBoxRef.bottom, margin = Space.spaceSmall)
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
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .wrapContentWidth()
            .height(Size.sizeXSmall)
            .constrainAs(progressRef) {
                top.linkTo(modelRef.bottom, margin = Space.spaceSmall)
                start.linkTo(parent.start, margin = Space.spaceMedium)
                end.linkTo(progressRef.end, margin = Space.spaceSmall)
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
        style = Typography.caption,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.constrainAs(nextBillingRef) {
            top.linkTo(priceRef.bottom, margin = Space.space2XSmall)
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
        style = Typography.body1,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.constrainAs(billingDateRef) {
            top.linkTo(nextBillingRef.bottom, margin = Space.space2XSmall)
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
        style = Typography.caption,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.constrainAs(progressTextRef) {
            top.linkTo(progressRef.top)
            bottom.linkTo(progressRef.bottom)
            end.linkTo(parent.end)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoanCardComposable() {
    // Sample Subscription data for the preview
    val sampleSubscription = Subscription(
        priceText = "$1,500",
        modelText = "Car Loan",
        nextText = "Next Billing: 01/15/2024",
        billingDateText = "Billing Date: 01/15/2023",
        progressText = "60% Paid",
        progressFraction = 0.6,
        imageContentDescription = "Car Image",
        imageResourceId = "sample_car_image_resource" // Replace with actual resource ID
    )

    LoanCardComposable(
        content = sampleSubscription,
        modifier = Modifier
            .width(Size.size2XMax)
            .height(Size.sizeMax)
            .clip(RoundedCornerShape(Size.sizeSmall))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(Size.sizeMedium)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCardImageBox() {
    ConstraintLayout {
        val imageBoxRef = createRef()
        CardImageBox(
            imageResourceId = R.drawable.ic_car,
            imageContentDescription = "Sample Image",
            imageBoxRef = imageBoxRef
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardPriceText() {
    ConstraintLayout {
        val priceRef = createRef()
        CardPriceText(
            priceText = "$999",
            priceRef = priceRef
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardModelText() {
    ConstraintLayout {
        val modelRef = createRef()
        val imageBoxRef = createRef()
        CardModelText(
            modelText = "Car Model X",
            modelRef = modelRef,
            imageBoxRef = imageBoxRef
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardProgressIndicator() {
    ConstraintLayout {
        val progressRef = createRef()
        val modelRef = createRef()
        CardProgressIndicator(
            progressFraction = 0.5f,
            progressRef = progressRef,
            modelRef = modelRef
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardNextBillingText() {
    ConstraintLayout {
        val nextBillingRef = createRef()
        val priceRef = createRef()
        CardNextBillingText(
            nextText = "Next Billing: 10/10/2023",
            nextBillingRef = nextBillingRef,
            priceRef = priceRef
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardBillingDateText() {
    ConstraintLayout {
        val billingDateRef = createRef()
        val nextBillingRef = createRef()
        CardBillingDateText(
            billingDateText = "Billing Date: 09/10/2023",
            billingDateRef = billingDateRef,
            nextBillingRef = nextBillingRef
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardProgressText() {
    ConstraintLayout {
        val progressTextRef = createRef()
        val progressRef = createRef()
        CardProgressText(
            progressText = "Progress: 50%",
            progressTextRef = progressTextRef,
            progressRef = progressRef
        )
    }
}
