package com.ouday.cryptowalletsample.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.stringResource
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.ui.theme.*
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.imageLoader
import coil.request.ImageRequest
import com.ouday.cryptowalletsample.common.getImageUrl

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
            .width(Size.size2XMax)
            .height(Size.sizeMax)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Size.sizeSmall))
                .fillMaxWidth()
                .background(Colors.primary.copy(alpha = if (isSelected) 0.8f else 0.2f))
                .wrapContentHeight()
        ) {
            IconButton(onClick = { onBillClicked(bill) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painter,
                        contentDescription = stringResource(id = R.string.bill_option_description, bill.billName),
                        modifier = Modifier
                            .size(Size.sizeLarge)
                            .padding(Space.space2XSmall),
                        tint = if (isSelected) Colors.background else Colors.onSurface
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
            style = Typography.caption.copy(fontSize = Typography.caption.fontSize)
        )
    }
}
