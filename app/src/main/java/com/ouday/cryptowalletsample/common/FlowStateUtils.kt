package com.ouday.cryptowalletsample.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ouday.cryptowalletsample.ui.theme.craneColors

@Composable
fun <T> HandleFlowState(
    flowState: FlowState<T>,
    onSuccess: @Composable (T) -> Unit,
    onRetry: () -> Unit
) {
    when (flowState) {
        is FlowState.Loading -> ShimmerEffect()
        is FlowState.Success -> onSuccess(flowState.data)
        is FlowState.Error -> ErrorContent(errorMessage = flowState.message, onRetry = onRetry)
    }
}

/**
 * Shimmer effect for the loading state.
 */
@Composable
fun ShimmerEffect() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.9f)
    )
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = -1000f,  // Start off-screen
        targetValue = 1000f,    // Move to the other side
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = LinearEasing),
            RepeatMode.Restart
        ), label = ""
    )

    ShimmerItem(brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,  // Fixed start point
        end = Offset(x = translateAnim, y = 0f)  // Animated end point
    ))
}


@Composable
fun ShimmerItem(brush: Brush) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Adjust these placeholders to match your content layout
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(brush))
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(brush))
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(brush))
    }
}


/**
 * Error content for the error state.
 * Displays an error message and provides a retry button.
 *
 * @param errorMessage The error message to display.
 * @param onRetry The function to call when the retry button is clicked.
 */
@Composable
fun ErrorContent(errorMessage: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Red, RoundedCornerShape(8.dp)) // Adding a border
            .padding(16.dp), // Additional padding inside the border,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Error icon or image (optional)
        Icon(Icons.Filled.Error, contentDescription = "Error", tint = craneColors.error)

        Spacer(modifier = Modifier.height(16.dp))

        // Error message text
        Text(
            text = errorMessage,
            color = craneColors.error,
            style = typography.body1,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Retry button
        Button(onClick = onRetry) {
            Text("Retry", color = Color.White)
        }
    }
}
