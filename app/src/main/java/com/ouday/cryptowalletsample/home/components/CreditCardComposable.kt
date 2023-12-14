import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.ui.viewmodel.CreditCardViewModel
import com.ouday.cryptowalletsample.ui.theme.*

@Composable
fun CreditCardComposable(cardInfo: CreditCardInfo, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(200.dp) // Adjust the height as needed
            .clip(RoundedCornerShape(16.dp)) // Adjust corner size as needed
            .background(Color.Black) // Use your theme's color
            .padding(16.dp) // Use your theme's padding
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (cardType, cardNumber, dueDate, amount, payButton, paymentStatus) = createRefs()

            Text(
                text = cardInfo.cardType,
                color = Color.White,
                style = craneTypography.h6, // Use your theme's typography
                modifier = Modifier.constrainAs(cardType) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(
                text = "**** 3854", // Masked number for privacy
                color = Color.White,
                style = craneTypography.h6, // Use your theme's typography
                modifier = Modifier.constrainAs(cardNumber) {
                    top.linkTo(cardType.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
            )

            Text(
                text = "Due Date ${cardInfo.dueDate}",
                color = Color.White,
                style = craneTypography.subtitle1, // Use your theme's typography
                modifier = Modifier.constrainAs(dueDate) {
                    top.linkTo(cardNumber.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Text(
                text = cardInfo.amountDue,
                color = Color.White,
                style = craneTypography.h4.copy(fontSize = 30.sp, fontWeight = FontWeight.Bold), // Use your theme's typography
                modifier = Modifier.constrainAs(amount) {
                    top.linkTo(dueDate.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )

            Button(
                onClick = { /* Handle the payment action */ },
                modifier = Modifier.constrainAs(payButton) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
            ) {
                Text(
                    text = "PAY",
                    style = craneTypography.button, // Use your theme's typography
                    color = Color.White
                )
            }

            Text(
                text = cardInfo.paymentStatus,
                color = Color.White,
                style = craneTypography.overline, // Use your theme's typography
                modifier = Modifier.constrainAs(paymentStatus) {
                    start.linkTo(parent.start, margin = 16.dp)
                    bottom.linkTo(payButton.top, margin = 8.dp)
                }
            )
        }
    }
}
