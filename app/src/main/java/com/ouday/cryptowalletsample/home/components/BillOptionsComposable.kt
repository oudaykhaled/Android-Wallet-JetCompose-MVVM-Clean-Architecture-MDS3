package com.ouday.cryptowalletsample.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.craneColors
import com.ouday.cryptowalletsample.ui.theme.craneTypography
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
            .width(40.dp)
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Size.sizeSmall))
                .fillMaxWidth()
                .background(craneColors.primary.copy(alpha = if (isSelected) 0.8f else 0.2f))
                .wrapContentHeight()
        ) {
            IconButton(onClick = { onBillClicked(bill) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painter,
                        contentDescription = bill.billName,
                        modifier = Modifier
                            .size(Size.sizeLarge)
                            .padding(Space.space2XSmall),
                        tint = if (isSelected) craneColors.background else craneColors.onSurface
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
            style = craneTypography.caption.copy(fontSize = 8.sp)
        )
    }
}