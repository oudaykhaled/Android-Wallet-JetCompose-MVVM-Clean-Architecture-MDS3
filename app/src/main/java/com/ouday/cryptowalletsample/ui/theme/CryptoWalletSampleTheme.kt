package com.ouday.cryptowalletsample.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val Colors.success: Color
    get() = Color(0xFF5DB83B)

val craneColors = lightColors(
    primary = Color(0xFF5D1049),
    primaryVariant = Color(0x2D5D1049),
    secondary = Color(0xFFE30425),
    surface = Color(0x65720D5D),
    onSurface = Color(0xFF200217),
    onBackground = Color(0xFF000000)
)

val BottomSheetShape = RoundedCornerShape(
    topStart = 20.dp,
    topEnd = 20.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)

object Size {
    val sizeXSmall = 4.dp
    val sizeSmall = 8.dp
    val sizeMedium = 16.dp
    val sizeLarge = 24.dp
    val sizeXLarge = 32.dp
    val size2XLarge = 48.dp
    val size3XLarge = 64.dp
    val size4XLarge = 96.dp
    val sizeMax = 128.dp
    val sizeXMax = 200.dp
    val size2XMax = 240.dp
}

object Space {
    val space2XSmall = 4.dp
    val spaceXSmall = 8.dp
    val spaceSmall = 12.dp
    val spaceMedium = 16.dp
    val spaceLarge = 24.dp
    val spaceXLarge = 32.dp
    val space2XLarge = 40.dp
    val space3XLarge = 48.dp
    val space4XLarge = 56.dp
    val spaceMax = 64.dp
}

@Composable
fun CryptoWalletSampleTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = craneColors, typography = craneTypography) {
        content()
    }
}
