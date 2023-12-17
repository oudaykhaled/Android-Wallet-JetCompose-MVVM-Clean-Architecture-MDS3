package com.ouday.cryptowalletsample.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.ui.theme.Size
import com.ouday.cryptowalletsample.ui.theme.Space
import com.ouday.cryptowalletsample.ui.theme.Typography

@Composable
fun HomeHeaderComposable(username: String, modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier) {
        val (imgProfile, txtUsername, imgNotification, imgSearch) = createRefs()

        ProfileImage(modifier = Modifier.constrainAs(imgProfile) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        UsernameText(username, modifier = Modifier.constrainAs(txtUsername) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(imgProfile.end)
        })

        NotificationIcon(modifier = Modifier.constrainAs(imgNotification) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        })

        SearchIcon(modifier = Modifier.constrainAs(imgSearch) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(imgNotification.start)
        })
    }
}

@Composable
fun ProfileImage(modifier: Modifier) {
    Box(
        modifier = modifier.padding(
            top = Space.spaceMedium,
            bottom = Space.spaceMedium,
            start = Space.spaceMedium,
            end = Space.space2XSmall
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.victory),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(Size.sizeXLarge)
        )
    }
}

@Composable
fun UsernameText(username: String, modifier: Modifier) {
    Text(
        text = username,
        modifier = modifier.padding(start = Space.spaceMedium),
        style = Typography.h5.copy(color = MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun NotificationIcon(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_notification),
        contentDescription = "Notification",
        modifier = modifier.padding(Space.spaceMedium)
    )
}

@Composable
fun SearchIcon(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "Search",
        modifier = modifier.padding(Space.spaceMedium)
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeHeaderComposable() {
    HomeHeaderComposable(
        username = "JohnDoe",
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileImage() {
    ProfileImage(modifier = Modifier.wrapContentSize())
}

@Preview(showBackground = true)
@Composable
fun PreviewUsernameText() {
    UsernameText(
        username = "JohnDoe",
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNotificationIcon() {
    NotificationIcon(
        modifier = Modifier.wrapContentSize()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchIcon() {
    SearchIcon(
        modifier = Modifier.wrapContentSize()
    )
}

