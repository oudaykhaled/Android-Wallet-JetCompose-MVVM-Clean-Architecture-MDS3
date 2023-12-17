package com.ouday.cryptowalletsample.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.imageLoader
import coil.request.ImageRequest
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.model.History
import com.ouday.cryptowalletsample.common.getImageUrl
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.Typography

@Composable
fun BillOptionItem(
    bill: Bill,
    isSelected: Boolean,
    onBillClicked: (Bill) -> Unit
) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    val painter: Painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(getImageUrl(bill.icon))
            .placeholder(R.drawable.ic_wifi)
            .build(),
        imageLoader = imageLoader
    )

    Column(
        modifier = Modifier
            .width(Size.size2XLarge)
            .height(Size.size3XLarge)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Size.sizeSmall))
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = if (isSelected) 0.8f else 0.2f))
                .wrapContentHeight()
        ) {
            IconButton(onClick = { onBillClicked(bill) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painter,
                        contentDescription = stringResource(id = R.string.bill_option_description, bill.billName),
                        modifier = Modifier
                            .size(Size.size2XLarge)
                            .padding(Space.space2XSmall),
                        tint = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
        Text(
            text = bill.billName,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            textAlign = TextAlign.Center,
            style = Typography.overline,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBillOptionItem() {
    // Sample Bill data for the preview
    val sampleBill = Bill(
        id = 1,
        billName = "Sample Bill",
        icon = "https://example.com/sample_icon.svg",
        history = listOf(
            History(date = "2023-12-17", amount = 100.0, paid = true)
        )
    )

    // Preview of BillOptionItem in both selected and unselected states
    Column {
        BillOptionItem(
            bill = sampleBill,
            isSelected = true,
            onBillClicked = {}
        )
        Spacer(modifier = Modifier.height(Space.spaceMedium))
        BillOptionItem(
            bill = sampleBill,
            isSelected = false,
            onBillClicked = {}
        )
    }
}

