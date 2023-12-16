package com.ouday.cryptowalletsample.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val Colors.success: Color
    get() = Color(0xFF4CAF50) // Example: Changed to Material Green

val Colors = lightColors(
    primary = Color(0xFF6200EE), // Example: Changed to Material Deep Purple
    primaryVariant = Color(0xFF3700B3), // Example: Changed to a darker shade of Deep Purple
    secondary = Color(0xFF03DAC6), // Example: Changed to Material Teal
    surface = Color(0xFF121212), // Example: Changed to a dark surface color
    onSurface = Color(0xFFFFFFFF), // Example: Changed to White for contrast on dark surface
    onBackground = Color(0xFF000000) // Example: Black for background
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

object MaterialCornerRadius {
    val radiusExtraSmall = 4.dp
    val radiusSmall = 8.dp
    val radiusMedium = 12.dp
    val radiusLarge = 16.dp
    val radiusExtraLarge = 20.dp
}

object MaterialElevation {
    val elevationLevel1 = 2.dp // For components like buttons
    val elevationLevel2 = 4.dp // For small components like cards
    val elevationLevel3 = 6.dp // For medium components
    val elevationLevel4 = 8.dp // For large components
}

@Composable
fun CryptoWalletSampleTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = Colors, typography = Typography) {
        content()
    }
}
